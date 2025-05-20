package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.entity.GiaoDich;
import com.learn.start.entity.Users;
import com.learn.start.response.Res;
import com.learn.start.security.CustomUserDetails;
import com.learn.start.service.GiaoDichService;
import com.learn.start.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.learn.start.config.VnPayConfig.*;

@RestController
@RequestMapping("/vnpay")
public class VnpayController {
    @Autowired
    GiaoDichService giaoDichService;
    @Autowired
    private UserService userService;

    @GetMapping("/create-payment")
    public Res<?> createPayment(@RequestParam("amount") Double amount,
                                @RequestParam("vnp_CurrentUrl") String urlReturn,
                                HttpServletRequest request) throws UnsupportedEncodingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int idUser = userDetails.getId();
        System.out.println(urlReturn);
        String vnp_TxnRef = String.valueOf(System.currentTimeMillis());
        String vnp_OrderInfo = "Nạp Tiền Vào tài khoản: " + vnp_TxnRef;
        String vnp_IpAddr = request.getRemoteAddr();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf((long)(amount * 100)));// VND -> x100
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", urlReturn);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String vnp_CreateDate = LocalDateTime.now().format(formatter);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        GiaoDich giaoDich = new GiaoDich();
        giaoDich.setMaGD(vnp_TxnRef);
        giaoDich.setLoaiGiaoDich("Nạp Tiền");
        giaoDich.setThoiGian(Timestamp.valueOf(LocalDateTime.now()));
        giaoDich.setUsers(new Users(idUser));
        giaoDich.setNoiDung(vnp_OrderInfo);
        giaoDich.setTrangThai(0);
        giaoDich.setSoTien(amount);
        giaoDich = giaoDichService.createGiaoDich(giaoDich);
        if(giaoDich == null) {
            System.out.println("Giao dịch tạo thất bại! giaoDich = null");
            return new Res<>(false, MessageConstrains.ERROR, null);
        }
        System.out.println(giaoDich.getMaGD() +"2");
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        for (String name : fieldNames) {
            String value = vnp_Params.get(name);
            if (value != null && !value.isEmpty()) {
                hashData.append(name).append('=').append(URLEncoder.encode(value, StandardCharsets.UTF_8.toString()));
                hashData.append('&');
            }
        }
        hashData.setLength(hashData.length() - 1); // remove last &

        String secureHash = hmacSHA512(vnp_HashSecret, hashData.toString());
        vnp_Params.put("vnp_SecureHash", secureHash);

        String queryUrl = buildQueryUrl(vnp_Params);

        Map<String, String> result = new HashMap<>();
        result.put("paymentUrl", vnp_Url + "?" + queryUrl);
        return new Res<>(true, MessageConstrains.SUCCESS, result);
    }

    @GetMapping("/return")
    public Res<?> vnpayReturn(HttpServletRequest request) {
        Map<String, String> fields = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int idUser = userDetails.getId();

        for (Enumeration<String> en = request.getParameterNames(); en.hasMoreElements(); ) {
            String paramName = en.nextElement();
            String paramValue = request.getParameter(paramName);
            fields.put(paramName, paramValue);
        }

        String vnp_SecureHash = fields.remove("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");

        if (!isValidVnpayResponse(fields, vnp_SecureHash)) {
            return new Res<>(false, MessageConstrains.ERROR, "Dữ liệu bị chỉnh sửa hoặc chữ ký không hợp lệ!");
        }

        // Đọc các trường cần thiết
        String txnRef = fields.get("vnp_TxnRef");
        String amount = fields.get("vnp_Amount");
        String responseCode = fields.get("vnp_ResponseCode");
        String transactionStatus = fields.get("vnp_TransactionStatus");
        String bankCode = fields.get("vnp_BankCode");
        String payDate = fields.get("vnp_PayDate");

        GiaoDich giaoDich = giaoDichService.findGiaoDichById(txnRef);
        if (giaoDich == null) {
            return new Res<>(false, MessageConstrains.ERROR, "Không tìm thấy giao dịch tương ứng!");
        }
        if(giaoDich.getTrangThai() != 0){
            return new Res<>(false, MessageConstrains.ERROR, "Giao Dịch đã hoàn thành");
        }
        if(!userService.updateSoDu(idUser, Double.parseDouble(amount)/100)){
            return new Res<>(false, MessageConstrains.ERROR, "Không tìm thấy giao dịch tương ứng!");
        }
        if ("00".equals(responseCode) && "00".equals(transactionStatus)) {
            giaoDich.setTrangThai(1);
            giaoDich.setNoiDung(giaoDich.getNoiDung() + " - Ngân hàng: " + bankCode);
            giaoDich.setMaPhanHoi(transactionStatus);
            giaoDich.setMaTruyVan(responseCode);
            Double amountVnd = Double.parseDouble(amount) / 100;
            giaoDich.setSoTien(amountVnd);
            giaoDich.setMaPhanHoi(transactionStatus);
            giaoDich.setMaTruyVan(responseCode);
            giaoDich = giaoDichService.createGiaoDich(giaoDich);
            System.out.println(giaoDich.getMaGD());
            return new Res<>(true, MessageConstrains.SUCCESS, "Thanh toán thành công cho đơn hàng: " + txnRef + " (số tiền: " + amountVnd + "₫)");
        } else {
            giaoDich.setTrangThai(-1);
            giaoDich.setMaPhanHoi(transactionStatus);
            giaoDich.setMaTruyVan(responseCode);
            giaoDichService.createGiaoDich(giaoDich);

            return new Res<>(false, MessageConstrains.ERROR, "Thanh toán thất bại hoặc bị hủy");
        }
    }

    private boolean isValidVnpayResponse(Map<String, String> fields, String vnp_SecureHash) {
        SortedMap<String, String> sortedParams = new TreeMap<>(fields);

        // Ghép chuỗi tham số theo định dạng VNPAY yêu cầu
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8)).append("&");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1); // chỉ xóa dấu & nếu có
        }



        // Tính HMAC SHA512
        String hash = hmacSHA512(vnp_HashSecret, sb.toString());
        return hash.equalsIgnoreCase(vnp_SecureHash);
    }
    private String buildQueryUrl(Map<String, String> params) throws UnsupportedEncodingException {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            if (value != null) {
                query.append(key)
                        .append("=")
                        .append(URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
                        )
                        .append("&");
            }
        }
        query.setLength(query.length() - 1); // remove last &
        return query.toString();
    }

    private String hmacSHA512(String key, String data) {
        try {
            javax.crypto.Mac hmac512 = javax.crypto.Mac.getInstance("HmacSHA512");
            javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            hmac512.init(secretKeySpec);
            byte[] bytes = hmac512.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(bytes);
        } catch (Exception ex) {
            throw new RuntimeException("Lỗi tạo mã HMACSHA512", ex);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}
