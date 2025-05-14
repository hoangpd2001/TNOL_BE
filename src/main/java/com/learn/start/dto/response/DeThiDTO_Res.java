package com.learn.start.dto.response;

import java.util.Date;

public class DeThiDTO_Res {
    private Integer id;
    private String tenDeThi;
    private String tenGiaoVien;
    private String tenChuong;
    private Integer thoiGianLam;
    private Integer trangThai;
    private Double giaTien;
    private Date ngayTao;
    private Integer luotXem;
    private String anhGV;
    private String truongGV;
    private Integer soCauHoi;
    private Integer idGiaoVien;
    private String tenLop;
    private String tenMon;

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public Integer getIdGiaoVien() {
        return idGiaoVien;
    }

    public void setIdGiaoVien(Integer idGiaoVien) {
        this.idGiaoVien = idGiaoVien;
    }

    public String getTruongGV() {
        return truongGV;
    }

    public void setTruongGV(String truongGV) {
        this.truongGV = truongGV;
    }

    public Integer getSoCauHoi() {
        return soCauHoi;
    }

    public void setSoCauHoi(Integer soCauHoi) {
        this.soCauHoi = soCauHoi;
    }

    public String getAnhGV() {
        return anhGV;
    }

    public void setAnhGV(String anhGV) {
        this.anhGV = anhGV;
    }

    public DeThiDTO_Res() {}

    public Integer getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(Integer luotXem) {
        this.luotXem = luotXem;
    }

    public String getTenDeThi() {
        return tenDeThi;
    }

    public void setTenDeThi(String tenDeThi) {
        this.tenDeThi = tenDeThi;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public DeThiDTO_Res(Integer id, String tenDeThi, String tenGiaoVien, String tenChuong, Integer thoiGianLam, Integer trangThai, Double giaTien, Date ngayTao, Integer luotXem, String anhGV, String truongGV, Integer soCauHoi, Integer idGiaoVien, String tenLop, String tenMon) {
        this.id = id;
        this.tenDeThi = tenDeThi;
        this.tenGiaoVien = tenGiaoVien;
        this.tenChuong = tenChuong;
        this.thoiGianLam = thoiGianLam;
        this.trangThai = trangThai;
        this.giaTien = giaTien;
        this.ngayTao = ngayTao;
        this.luotXem = luotXem;
        this.anhGV = anhGV;
        this.truongGV = truongGV;
        this.soCauHoi = soCauHoi;
        this.idGiaoVien = idGiaoVien;
        this.tenLop = tenLop;
        this.tenMon = tenMon;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
