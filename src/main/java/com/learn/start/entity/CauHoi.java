package com.learn.start.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CAUHOI")
public class CauHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "IDCHUONG", referencedColumnName = "ID")
    private Chuong chuong;

    @ManyToOne
    @JoinColumn(name = "IDGIAOVIEN", referencedColumnName = "ID")
    private GiaoVien giaoVien;

    @Column(name = "MUCDO")
    private Integer mucDo;

    @Column(name = "DE")
    private String de;

    @Column(name = "A")
    private String a;

    @Column(name = "B")
    private String b;

    @Column(name = "C")
    private String c;

    @Column(name = "D")
    private String d;

    @Column(name = "DAPAN")
    private String dapAn;

    @Column(name = "HINHANH")
    private String hinhAnh;

    @Column(name = "TRANGTHAI")
    private int trangThai;

    @Column(name="CHITIET")
    private String chiTiet;
    @Column(name="HINHANHDA")
    private String hinhAnhDa;

    public String getHinhAnhDa() {
        return hinhAnhDa;
    }

    public void setHinhAnhDa(String hinhAnhDa) {
        this.hinhAnhDa = hinhAnhDa;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }


    public CauHoi(int id) {
        this.id = id;
    }

    public CauHoi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chuong getChuong() {
        return chuong;
    }

    public void setChuong(Chuong chuong) {
        this.chuong = chuong;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public Integer getMucDo() {
        return mucDo;
    }

    public void setMucDo(Integer mucDo) {
        this.mucDo = mucDo;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
