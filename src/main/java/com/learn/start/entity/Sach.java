package com.learn.start.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.NamedNativeQuery;
@Entity
@NamedStoredProcedureQuery(
        name = "Sach.getSachByMaSach",
        procedureName = "FN_GETSACHBYMASACH",
        resultClasses = Sach.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_MaSach", type = String.class)
        }
)

public class Sach implements Serializable {
    @Id
    @Column(name = "MASACH",nullable = false, unique = true)
    private String maSach;   // Assuming `MaSach` is the primary key of the view
    @Column(name = "TENSACH")
    private String tenSach;   // Other fields of the view
    @Column(name = "TACGIA")
    private String tacGia;
   @Column(name = "LOAISACH")
    private String loaiSach;
    @Column(name = "NXB")
    private String nxb;
    @Column(name = "SOLUONG")
    private Integer soLuong;
    @Column(name = "DONGIA")
    private Integer donGia;

    public Sach(String maSach, String tenSach, String tacGia, String loaiSach, String nxb, Integer soLuong, Integer donGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.loaiSach = loaiSach;
        this.nxb = nxb;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public Sach() {

    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }


}
