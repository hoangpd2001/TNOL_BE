package com.learn.start.entity;

import jakarta.persistence.*;

@Entity
@Table(name="GIAOVIEN")
//@DiscriminatorValue("GIAOVIEN")
@PrimaryKeyJoinColumn(name = "ID")
public class GiaoVien extends Users{
    @Column(name="CCCD")
    private String cccd;
    @Column(name="TKNGANHANG")
    private String tkNganHang;
    @Column(name="NGANHANG")
    private String nganHang;
    @Column(name="TRANGTHAI")
    private int trangThai;
    @Column(name="GHICHU")
    private String ghiChu;
    public GiaoVien() {
    }

    public GiaoVien(int id) {
        super(id);
    }
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
