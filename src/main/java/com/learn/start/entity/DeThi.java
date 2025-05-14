package com.learn.start.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "DETHI")
public class DeThi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name="TENDETHI")
    private String tenDeThi;

    public String getTenDeThi() {
        return tenDeThi;
    }

    public void setTenDeThi(String tenDeThi) {
        this.tenDeThi = tenDeThi;
    }

    @ManyToOne
    @JoinColumn(name = "IDGIAOVIEN", referencedColumnName = "ID")
    private GiaoVien giaoVien;

    @ManyToOne
    @JoinColumn(name = "IDCHUONG", referencedColumnName = "ID")
    private Chuong chuong;

    @Column(name = "THOIGIANLAM")
    private Integer thoiGianLam;

    @Column(name = "TRANGTHAI")
    private Integer trangThai;

    @Column(name = "GIATIEN")
    private Double giaTien;
    @Column(name="LUOTXEM")
    private Integer luotXem;

    public Integer getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(Integer luotXem) {
        this.luotXem = luotXem;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Column(name="NGAYTAO")
    @CreationTimestamp
    private Date ngayTao;
    public DeThi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public Chuong getChuong() {
        return chuong;
    }

    public void setChuong(Chuong chuong) {
        this.chuong = chuong;
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

    public DeThi() {
    }
}
