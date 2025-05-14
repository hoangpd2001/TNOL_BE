package com.learn.start.dto.response;

public class KQOnLuyenDTO_Res {

    private Integer idCauHoi;
    private Integer idOnLuyen;
    private String dapAn;

    public KQOnLuyenDTO_Res(Integer idCauHoi) {
        this.idCauHoi = idCauHoi;
    }

    public KQOnLuyenDTO_Res(Integer idCauHoi, Integer idOnLuyen, String dapAn) {
        this.idCauHoi = idCauHoi;
        this.idOnLuyen = idOnLuyen;
        this.dapAn = dapAn;
    }

    public Integer getIdCauHoi() {
        return idCauHoi;
    }

    public void setIdCauHoi(Integer idCauHoi) {
        this.idCauHoi = idCauHoi;
    }

    public Integer getIdOnLuyen() {
        return idOnLuyen;
    }

    public void setIdOnLuyen(Integer idOnLuyen) {
        this.idOnLuyen = idOnLuyen;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }
// Getter và Setter
}
