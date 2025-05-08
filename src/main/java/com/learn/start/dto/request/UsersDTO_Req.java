package com.learn.start.dto.request;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class UsersDTO_Req {
    private Integer id;
    @NotBlank
    private String hoTen;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    private String truong;

    @Pattern(regexp = "0\\d{9}",message = "số điện thoại không hợp lệ")
    private String sdt;

    @Email
    private String gmail;

    private String mk;

    private MultipartFile anh;

    private Integer role;
    private Integer soDu;

    public Integer getSoDu() {
        return soDu;
    }

    public void setSoDu(Integer soDu) {
        this.soDu = soDu;
    }

    public UsersDTO_Req() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTruong() {
        return truong;
    }

    public void setTruong(String truong) {
        this.truong = truong;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public MultipartFile getAnh() {
        return anh;
    }

    public void setAnh(MultipartFile anh) {
        this.anh = anh;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
