package com.learn.start.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CauHoiDeThiId implements Serializable {
    private Integer idCauHoi;
    private Integer idDeThi;

    public CauHoiDeThiId() {}

    public CauHoiDeThiId(Integer idCauHoi, Integer idDeThi) {
        this.idCauHoi = idCauHoi;
        this.idDeThi = idDeThi;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CauHoiDeThiId)) return false;
        CauHoiDeThiId that = (CauHoiDeThiId) o;
        return Objects.equals(idCauHoi, that.idCauHoi) && Objects.equals(idDeThi, that.idDeThi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCauHoi, idDeThi);
    }
}
