package com.learn.start.dto.request;

import jakarta.validation.constraints.NotNull;

public class KQOnLuyenDTO_Req {

    @NotNull(message = "ID câu hỏi không được để trống")
    private Integer idCauHoi;

    @NotNull(message = "ID ôn luyện không được để trống")
    private Integer idOnLuyen;

  //  @NotNull(message = "Đáp án không được để trống")
    private String dapAn;

    public KQOnLuyenDTO_Req() {
    }

    public KQOnLuyenDTO_Req(Integer idCauHoi, Integer idOnLuyen, String dapAn) {
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
}
