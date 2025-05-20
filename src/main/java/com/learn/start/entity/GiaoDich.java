package com.learn.start.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "GIAODICH")
public class GiaoDich {

    @Id
    @Column(name = "MAGD")
    private String maGD;

    @Column(name = "SOTIEN")
    private Double soTien;

    @Column(name = "THOIGIAN")
    private Timestamp thoiGian;

    @Column(name = "TRANGTHAI")
    private Integer trangThai;

    @Column(name = "MAPHANHOI")
    private String maPhanHoi;

    @Column(name = "MATRUYVAN")
    private String maTruyVan;

    @Column(name = "NOIDUNG")
    private String noiDung;

    @Column(name = "LOAIGIAODICH")
    private String loaiGiaoDich;

    @ManyToOne
    @JoinColumn(name = "IDUSERS", referencedColumnName = "ID")
    private Users users;

    public GiaoDich(String maGD) {
        this.maGD = maGD;
    }

    public GiaoDich() {
    }

    public GiaoDich(String maGD, Double soTien, Timestamp thoiGian, Integer trangThai, String maPhanHoi, String maTruyVan, String noiDung, String loaiGiaoDich, Users users) {
        this.maGD = maGD;
        this.soTien = soTien;
        this.thoiGian = thoiGian;
        this.trangThai = trangThai;
        this.maPhanHoi = maPhanHoi;
        this.maTruyVan = maTruyVan;
        this.noiDung = noiDung;
        this.loaiGiaoDich = loaiGiaoDich;
        this.users = users;
    }

    public String getMaGD() {
        return maGD;
    }

    public void setMaGD(String maGD) {
        this.maGD = maGD;
    }

    public Double getSoTien() {
        return soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
