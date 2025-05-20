package com.learn.start.dto.response;

import lombok.*;

import java.sql.Timestamp;


public class GiaoDichDTO_Res {

    private String maGD;
    private Long soTien;
    private Timestamp thoiGian;
    private String trangThai;
    private String maPhanHoi;
    private String maTruyVan;
    private String noiDung;
    private String loaiGiaoDich;
    private String maNguoiDung;

    public GiaoDichDTO_Res() {
    }

    public GiaoDichDTO_Res(String maGD) {
        this.maGD = maGD;
    }

    public GiaoDichDTO_Res(String maGD, Long soTien, Timestamp thoiGian, String trangThai, String maPhanHoi, String maTruyVan, String noiDung, String loaiGiaoDich, String maNguoiDung) {
        this.maGD = maGD;
        this.soTien = soTien;
        this.thoiGian = thoiGian;
        this.trangThai = trangThai;
        this.maPhanHoi = maPhanHoi;
        this.maTruyVan = maTruyVan;
        this.noiDung = noiDung;
        this.loaiGiaoDich = loaiGiaoDich;
        this.maNguoiDung = maNguoiDung;
    }

    public String getMaGD() {
        return maGD;
    }

    public void setMaGD(String maGD) {
        this.maGD = maGD;
    }

    public Long getSoTien() {
        return soTien;
    }

    public void setSoTien(Long soTien) {
        this.soTien = soTien;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaPhanHoi() {
        return maPhanHoi;
    }

    public void setMaPhanHoi(String maPhanHoi) {
        this.maPhanHoi = maPhanHoi;
    }

    public String getMaTruyVan() {
        return maTruyVan;
    }

    public void setMaTruyVan(String maTruyVan) {
        this.maTruyVan = maTruyVan;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
}
