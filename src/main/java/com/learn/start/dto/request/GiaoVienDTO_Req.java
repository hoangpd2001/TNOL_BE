package com.learn.start.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class GiaoVienDTO_Req extends UsersDTO_Req{

    @NotBlank(message="Vui lòng nhập đầy đủ thôn tin ")
    private String cccd;
    private String tkNganHang;
    private String nganHang;
    private Integer trangThai;
    private Integer soDu;
    private String ghiChu;

    public GiaoVienDTO_Req() {
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getTkNganHang() {
        return tkNganHang;
    }

    public void setTkNganHang(String tkNganHang) {
        this.tkNganHang = tkNganHang;
    }

    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getSoDu() {
        return soDu;
    }

    public void setSoDu(Integer soDu) {
        this.soDu = soDu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
