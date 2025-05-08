package com.learn.start.dto.response;

public class GiaoVienDTO_Res extends UsersDTO_Res {

    private String cccd;
    private String tkNganHang;
    private String nganHang;
    private int trangThai;
    private int soDu;



    private String ghiChu;
    public GiaoVienDTO_Res() {
    }

    public String getCccd() {
        return cccd;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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

    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }
}
