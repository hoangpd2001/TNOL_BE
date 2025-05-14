package com.learn.start.dto.request;

import jakarta.validation.constraints.NotNull;

public class CauHoiDeThiDTO_Req {

    @NotNull(message = "ID câu hỏi không được để trống")
    private Integer idCauHoi;


    private Integer idDeThi;

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
}
