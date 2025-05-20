package com.learn.start.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("auth/api/vnpay")
public class VnpayAuthController {

    private final String vnp_TmnCode = "3XUYJCSL";
    private final String vnp_HashSecret = "PPUXX797OJNX71TQZM4MVQ7C3X5C4031";
    private final String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    private final String vnp_ReturnUrl = "http://localhost:3001/vnpay";

    @GetMapping("/create-payment")
    public Map<String, String> createPayment(@RequestParam("amount") int amount,
                                             HttpServletRequest request) throws UnsupportedEncodingException {
        String vnp_TxnRef = String.valueOf(System.currentTimeMillis());
        String vnp_OrderInfo = "Thanh toan don hang: " + vnp_TxnRef;
        String vnp_IpAddr = request.getRemoteAddr();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount * 100)); // VND -> x100
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String vnp_CreateDate = LocalDateTime.now().format(formatter);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);


        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        for (String name : fieldNames) {
            String value = vnp_Params.get(name);
            if (value != null && !value.isEmpty()) {
                hashData.append(name).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII.toString()));
                hashData.append('&');
            }
        }
        hashData.setLength(hashData.length() - 1); // remove last &

        String secureHash = hmacSHA512(vnp_HashSecret, hashData.toString());
        vnp_Params.put("vnp_SecureHash", secureHash);

        String queryUrl = buildQueryUrl(vnp_Params);

        Map<String, String> result = new HashMap<>();
        result.put("paymentUrl", vnp_Url + "?" + queryUrl);
        return result;
    }

    @GetMapping("/return")
    public String vnpayReturn(HttpServletRequest request) {
        Map<String, String> fields = new HashMap<>();

        // Lấy toàn bộ tham số từ URL
        for (Enumeration<String> en = request.getParameterNames(); en.hasMoreElements(); ) {
            String paramName = en.nextElement();
            String paramValue = request.getParameter(paramName);
            fields.put(paramName, paramValue);
        }

        // Lấy SecureHash từ VNPAY gửi về
        String vnp_SecureHash = fields.remove("vnp_SecureHash");

        // Kiểm tra tính toàn vẹn của dữ liệu
        if (!isValidVnpayResponse(fields, vnp_SecureHash)) {
            return "❌ Dữ liệu bị chỉnh sửa hoặc chữ ký không hợp lệ!";
        }

        // Xử lý logic thanh toán nếu chữ ký hợp lệ
        String txnRef = fields.get("vnp_TxnRef");
        String amount = fields.get("vnp_Amount");
        String responseCode = fields.get("vnp_ResponseCode");
        String transactionStatus = fields.get("vnp_TransactionStatus");

        if ("00".equals(responseCode) && "00".equals(transactionStatus)) {

            return "✅ Thanh toán thành công cho đơn hàng: " + txnRef + " (số tiền: " + amount + ")";
        } else {
            return "❌ Thanh toán thất bại hoặc bị hủy!";
        }
    }
    private boolean isValidVnpayResponse(Map<String, String> fields, String vnp_SecureHash) {
        SortedMap<String, String> sortedParams = new TreeMap<>(fields);

        // Ghép chuỗi tham số theo định dạng VNPAY yêu cầu
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.setLength(sb.length() - 1); // bỏ dấu & cuối

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
