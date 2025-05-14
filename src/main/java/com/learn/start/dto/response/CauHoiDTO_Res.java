package com.learn.start.dto.response;

public class CauHoiDTO_Res {
    private Integer id;
    private String de;
    private Integer mucDo;
    private String a, b, c, d;
    private String dapAn;
    private String hinhAnh;
    private String tenGiaoVien;
    private String tenChuong;
    private Integer trangThai;
    private String chiTiet;
    private String hinhAnhDa;
    private String daOnLuyen;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDaOnLuyen() {
        return daOnLuyen;
    }

    public void setDaOnLuyen(String daOnLuyen) {
        this.daOnLuyen = daOnLuyen;
    }

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

    public CauHoiDTO_Res() {}

    public CauHoiDTO_Res(int id, String de, Integer mucDo, String a, String b, String c, String d, String dapAn, String hinhAnh, String tenGiaoVien, String tenChuong, Integer trangThai, String chiTiet) {
        this.id = id;
        this.de = de;
        this.mucDo = mucDo;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dapAn = dapAn;
        this.hinhAnh = hinhAnh;
        this.tenGiaoVien = tenGiaoVien;
        this.tenChuong = tenChuong;
        this.trangThai = trangThai;
        this.chiTiet = chiTiet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public Integer getMucDo() {
        return mucDo;
    }

    public void setMucDo(Integer mucDo) {
        this.mucDo = mucDo;
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

    public String getTenGiaoVien() {
        return tenGiaoVien;
    }

    public void setTenGiaoVien(String tenGiaoVien) {
        this.tenGiaoVien = tenGiaoVien;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
// Getters & Setters ...
}
