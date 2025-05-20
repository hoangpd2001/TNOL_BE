package com.learn.start.service;

import com.learn.start.dto.request.GiaoVienDTO_Req;
import com.learn.start.dto.response.GiaoVienDTO_Res;
import com.learn.start.entity.GiaoVien;
import com.learn.start.entity.Role;
import com.learn.start.entity.Users;
import com.learn.start.repository.GiaoVienRepository;
import com.learn.start.repository.UsersRepository;
import com.learn.start.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GiaoVienService {
    @Autowired
    private GiaoVienRepository giaoVienRepository;

    public List<GiaoVienDTO_Res> getGiaoVien() {
        List<GiaoVien> list = giaoVienRepository.findAll();
        List<GiaoVienDTO_Res> resList = new ArrayList<>();
        for (GiaoVien giaoVien : list) {
            GiaoVienDTO_Res res = new GiaoVienDTO_Res();
            res.setId(giaoVien.getId());
            res.setHoTen(giaoVien.getHoTen());
            res.setTruong(giaoVien.getTruong());
            res.setTrangThai(giaoVien.getTrangThai());
            if(giaoVien.getAnh() != null){
                res.setAnh("/uploads/" + giaoVien.getAnh());
            }
            resList.add(res);
        }
        return resList;

    }
    public GiaoVienDTO_Res getGiaoVienById(Integer id) {
        Optional<GiaoVien> gv = giaoVienRepository.findById(id);
        if (gv.isPresent()) {
            GiaoVienDTO_Res res = new GiaoVienDTO_Res();
            res.setId(gv.get().getId());
            res.setHoTen(gv.get().getHoTen());
            res.setTruong(gv.get().getTruong());
           // res.setCccd(gv.get().getCccd());
           // res.setSdt(gv.get().getSdt());
            res.setGmail(gv.get().getGmail());
            res.setTrangThai(gv.get().getTrangThai());
            res.setAnh( gv.get().getAnh());
            res.setGhiChu(gv.get().getGhiChu());

            return res;
        }
        return null;
    }
    public GiaoVienDTO_Res getGiaoVienById2(Integer id) {
        Optional<GiaoVien> gv = giaoVienRepository.findById(id);
        if (gv.isPresent()) {
            GiaoVienDTO_Res res = new GiaoVienDTO_Res();
            res.setId(gv.get().getId());
            res.setHoTen(gv.get().getHoTen());
            res.setTruong(gv.get().getTruong());
            // res.setCccd(gv.get().getCccd());
            // res.setSdt(gv.get().getSdt());
            res.setGmail(gv.get().getGmail());
            res.setTrangThai(gv.get().getTrangThai());
            res.setAnh( gv.get().getAnh());
            res.setGhiChu(gv.get().getGhiChu());
            return res;
        }
        return null;
    }
    public GiaoVienDTO_Res CreateGiaoVien(GiaoVienDTO_Req req) throws ParseException {
        GiaoVien giaoVien = new GiaoVien();
        giaoVien.setHoTen(req.getHoTen());
        giaoVien.setNgaySinh(req.getNgaySinh());
        giaoVien.setCccd(req.getCccd());
        giaoVien.setSdt(req.getSdt());
        giaoVien.setTruong(req.getTruong());
        giaoVien.setGmail(req.getGmail());
        String MkHash = PasswordUtil.hashPassword(req.getMk());
        giaoVien.setMk(MkHash);
        giaoVien.setGhiChu(null);
        giaoVien.setTkNganHang(null);
        giaoVien.setNganHang(null);
        giaoVien.setTrangThai(1);
        giaoVien.setSoDu(0D);
        giaoVien.setRole(new Role(2));
       giaoVien.setAnh("");

        giaoVien = giaoVienRepository.save(giaoVien);
        MultipartFile anh = req.getAnh();
        if (anh != null && !anh.isEmpty()) {
            String folderName = "giaovien";
            String fileExtension = Objects.requireNonNull(anh.getOriginalFilename())
                    .substring(anh.getOriginalFilename().lastIndexOf("."));
            String fileName = giaoVien.getId() + fileExtension;

            Path uploadPath = Paths.get("uploads", folderName);
            try {
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Files.copy(anh.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

                // Gán lại tên file vào DB
                giaoVien.setAnh(folderName + "/" + fileName);
                giaoVienRepository.save(giaoVien); // Cập nhật lại ảnh
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        GiaoVienDTO_Res res = new GiaoVienDTO_Res();
        res.setId(giaoVien.getId());
        res.setHoTen(giaoVien.getHoTen());
        res.setTruong(giaoVien.getTruong());
        res.setCccd(giaoVien.getCccd());
        res.setSdt(giaoVien.getSdt());
        res.setGmail(giaoVien.getGmail());
        res.setTrangThai(giaoVien.getTrangThai());
        res.setAnh(giaoVien.getAnh());
        return res;

    }
    public GiaoVienDTO_Res UpdateGiaoVien(GiaoVienDTO_Req req) {
//        GiaoVien giaoVien = new GiaoVien();
//        giaoVien.setId(req.getId());
//        giaoVien.setHoTen(req.getHoTen());
//        giaoVien.setTruong(req.getTruong());
//        giaoVien.setCccd(req.getCccd());
//        giaoVien.setSdt(req.getSdt());
//        giaoVien.setGmail(req.getGmail());
//        giaoVien.setMk(req.getMk());
//        giaoVien.setTkNganHang(req.getTkNganHang());
//        giaoVien.setNganHang(req.getNganHang());
//        giaoVien.setTrangThai(req.getTrangThai());
//        GiaoVien gv =  giaoVienRepository.save(giaoVien);
//        GiaoVienDTO_Res res = new GiaoVienDTO_Res();
//        res.setId(gv.getId());
//        res.setHoTen(gv.getHoTen());
//        res.setTruong(gv.getTruong());
//        res.setCccd(gv.getCccd());
//        res.setSdt(gv.getSdt());
//        res.setGmail(gv.getGmail());
//        res.setTrangThai(gv.getTrangThai());
//        return res;
        return null;
    }
    public String generateNewGiaoVienId() {
//        String lastId = giaoVienRepository.findLastId();
//        int idNumber = Integer.parseInt(lastId);
//        idNumber++;
//        return String.format("%06d", idNumber);
        return null;
    }

    public GiaoVienDTO_Res getGiaoVienById(GiaoVienDTO_Req req) {
//        Optional<GiaoVien> optionalGv = giaoVienRepository.findById(req.getId());
//        if (!optionalGv.isPresent()) {
//            throw new RuntimeException("Không tìm thấy giáo viên với ID: " + req.getId());
//        }
//
//        GiaoVien giaoVien = optionalGv.get();
//
//        // Chỉ cập nhật nếu giá trị mới không null
//        if (req.getHoTen() != null) giaoVien.setHoTen(req.getHoTen());
//        if (req.getTruong() != null) giaoVien.setTruong(req.getTruong());
//        if (req.getCccd() != null) giaoVien.setCccd(req.getCccd());
//        if (req.getSdt() != null) giaoVien.setSdt(req.getSdt());
//        if (req.getGmail() != null) giaoVien.setGmail(req.getGmail());
//        if (req.getMk() != null) giaoVien.setMk(req.getMk());
//        if (req.getTkNganHang() != null) giaoVien.setTkNganHang(req.getTkNganHang());
//        if (req.getNganHang() != null) giaoVien.setNganHang(req.getNganHang());
//        if (req.getTrangThai() != null) giaoVien.setTrangThai(req.getTrangThai());
//        GiaoVien updatedGv = giaoVienRepository.save(giaoVien);
//
//        // Trả về DTO Response
//        GiaoVienDTO_Res res = new GiaoVienDTO_Res();
//        res.setId(updatedGv.getId());
//        res.setHoTen(updatedGv.getHoTen());
//        res.setTruong(updatedGv.getTruong());
//        res.setCccd(updatedGv.getCccd());
//        res.setSdt(updatedGv.getSdt());
//        res.setGmail(updatedGv.getGmail());
//        res.setTrangThai(updatedGv.getTrangThai());
//
//        return res;
        return null;
    }

}
