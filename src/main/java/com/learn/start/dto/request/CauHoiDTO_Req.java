package com.learn.start.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class CauHoiDTO_Req {
    private Integer id;
    private Integer soThuTu;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(Integer soThuTu) {
        this.soThuTu = soThuTu;
    }

    @NotNull(message = "ID chương không được để trống")
    private Integer idChuong;

    @NotNull(message = "ID giáo viên không được để trống")
    private Integer idGiaoVien;

    @NotBlank(message = "Mức độ không được để trống")
    private Integer mucDo;

    @NotBlank(message = "Nội dung đề không được để trống")
    private String de;

    @NotBlank private String a;
    @NotBlank private String b;
    @NotBlank private String c;
    @NotBlank private String d;

    @NotBlank(message = "Đáp án không được để trống")
    private String dapAn;

    private MultipartFile  hinhAnh;

    private Integer trangThai;

    private String chiTiet;
    private MultipartFile   hinhAnhDa;

    public MultipartFile  getHinhAnhDa() {
        return hinhAnhDa;
    }

    public void setHinhAnhDa(MultipartFile  hinhAnhDa) {
        this.hinhAnhDa = hinhAnhDa;
    }
    public String getChiTiet() {
        return chiTiet;
    }

    public CauHoiDTO_Req(int id) {
        this.id = id;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public CauHoiDTO_Req() {}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdChuong() {
        return idChuong;
    }

    public void setIdChuong(Integer idChuong) {
        this.idChuong = idChuong;
    }

    public Integer getIdGiaoVien() {
        return idGiaoVien;
    }

    public void setIdGiaoVien(Integer idGiaoVien) {
        this.idGiaoVien = idGiaoVien;
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

    public MultipartFile getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(MultipartFile  hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
// Getters & Setters ...
}
