package com.learn.start.dto.response;

import java.time.LocalDateTime;

public class OnLuyenDTO_Res {

    private Integer id;
    private Integer idDeThi;
    private Integer idUser;
    private LocalDateTime thoiGianBatDau;
    private LocalDateTime thoiGianKetThuc;
    private Integer trangThai;
    private Integer tongCauHoi;
    private Integer traLoiDung;
    private String tenDeThi;
    private String tenChuong;
    private String tenMon;
    private String tenLop;

    public OnLuyenDTO_Res(Integer id, Integer idDeThi, Integer idUser, LocalDateTime thoiGianBatDau, LocalDateTime thoiGianKetThuc, Integer trangThai, Integer tongCauHoi, Integer traLoiDung, String tenDeThi, String tenChuong, String tenMon, String tenLop, String tenGiaoVien) {
        this.id = id;
        this.idDeThi = idDeThi;
        this.idUser = idUser;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.trangThai = trangThai;
        this.tongCauHoi = tongCauHoi;
        this.traLoiDung = traLoiDung;
        this.tenDeThi = tenDeThi;
        this.tenChuong = tenChuong;
        this.tenMon = tenMon;
        this.tenLop = tenLop;
        this.tenGiaoVien = tenGiaoVien;
    }

    private String tenGiaoVien;

    public String getTenGiaoVien() {
        return tenGiaoVien;
    }

    public void setTenGiaoVien(String tenGiaoVien) {
        this.tenGiaoVien = tenGiaoVien;
    }



    public Integer getTongCauHoi() {
        return tongCauHoi;
    }

    public void setTongCauHoi(Integer tongCauHoi) {
        this.tongCauHoi = tongCauHoi;
    }

    public Integer getTraLoiDung() {
        return traLoiDung;
    }

    public void setTraLoiDung(Integer traLoiDung) {
        this.traLoiDung = traLoiDung;
    }

    public String getTenDeThi() {
        return tenDeThi;
    }

    public void setTenDeThi(String tenDeThi) {
        this.tenDeThi = tenDeThi;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public OnLuyenDTO_Res() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDeThi() {
        return idDeThi;
    }

    public void setIdDeThi(Integer idDeThi) {
        this.idDeThi = idDeThi;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}
