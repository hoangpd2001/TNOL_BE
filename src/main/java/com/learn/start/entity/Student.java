package com.learn.start.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sinhvien") // Tên bảng trong database
public class Student {

    @Id
    @Column(name = "MASV",nullable = false, unique = true)
    private String MaSV;

    @Column(name = "HOTEN", nullable = false)
    private String HoTen;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "NGAYSINH")
    private Date NgaySinh;

    @Column(name = "GIOITINH")
    private String GioiTinh;

    @Column(name = "DIACHI")
    private String DiaChi;

    @Column(name = "SODIENTHOAI")
    private String SoDienThoai;

    @Column(name = "LOP")
    private String Lop;

    public Student() {
    }

    public Student(String maSV, String hoTen, Date ngaySinh, String gioiTinh, String diaChi, String soDienThoai, String lop) {
        this.MaSV = maSV;
        this.HoTen = hoTen;
        this.NgaySinh = ngaySinh;
        this.GioiTinh = gioiTinh;
        this.DiaChi = diaChi;
        this.SoDienThoai = soDienThoai;
        this.Lop = lop;
    }


    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        this.MaSV = maSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        this.HoTen = hoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.NgaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.GioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        this.DiaChi = diaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.SoDienThoai = soDienThoai;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        this.Lop = lop;
    }
}
