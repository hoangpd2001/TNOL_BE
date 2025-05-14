package com.learn.start.dto.request;

import jakarta.validation.constraints.NotNull;

public class DeThiDTO_Req {
    private Integer id;

    private String tenDeThi;

    public String getTenDeThi() {
        return tenDeThi;
    }

    public void setTenDeThi(String tenDeThi) {
        this.tenDeThi = tenDeThi;
    }

    private Integer idGiaoVien;

    @NotNull(message = "ID chương không được để trống")
    private Integer idChuong;

    @NotNull(message = "Thời gian làm không được để trống")
    private Integer thoiGianLam;

    private Integer trangThai;

    private Double giaTien;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGiaoVien() {
        return idGiaoVien;
    }

    public void setIdGiaoVien(Integer idGiaoVien) {
        this.idGiaoVien = idGiaoVien;
    }

    public Integer getIdChuong() {
        return idChuong;
    }

    public void setIdChuong(Integer idChuong) {
        this.idChuong = idChuong;
    }

    public Integer getThoiGianLam() {
        return thoiGianLam;
    }

    public void setThoiGianLam(Integer thoiGianLam) {
        this.thoiGianLam = thoiGianLam;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public DeThiDTO_Req() {
    }

    public DeThiDTO_Req(Integer id) {
        this.id = id;
    }
}
