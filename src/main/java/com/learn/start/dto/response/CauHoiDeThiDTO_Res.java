package com.learn.start.dto.response;

public class CauHoiDeThiDTO_Res {
    private Integer idCauHoi;
    private Integer idDeThi;
    private String noiDungCauHoi;
    private String tenDeThi;

    public CauHoiDeThiDTO_Res() {}

    public CauHoiDeThiDTO_Res(Integer idCauHoi, Integer idDeThi, String noiDungCauHoi, String tenDeThi) {
        this.idCauHoi = idCauHoi;
        this.idDeThi = idDeThi;
        this.noiDungCauHoi = noiDungCauHoi;
        this.tenDeThi = tenDeThi;
    }

    public Integer getIdCauHoi() {
        return idCauHoi;
    }

    public void setIdCauHoi(Integer idCauHoi) {
        this.idCauHoi = idCauHoi;
    }

    public Integer getIdDeThi() {
        return idDeThi;
    }

    public void setIdDeThi(Integer idDeThi) {
        this.idDeThi = idDeThi;
    }

    public String getNoiDungCauHoi() {
        return noiDungCauHoi;
    }

    public void setNoiDungCauHoi(String noiDungCauHoi) {
        this.noiDungCauHoi = noiDungCauHoi;
    }

    public String getTenDeThi() {
        return tenDeThi;
    }

    public void setTenDeThi(String tenDeThi) {
        this.tenDeThi = tenDeThi;
    }
}
