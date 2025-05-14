package com.learn.start.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KQOnLuyenID {
    private Integer idCauHoi;
    private Integer idOnLuyen;

    public KQOnLuyenID(Integer idCauHoi, Integer idOnLuyen) {
        this.idCauHoi = idCauHoi;
        this.idOnLuyen = idOnLuyen;
    }

    public KQOnLuyenID() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KQOnLuyenID)) return false;
        KQOnLuyenID that = (KQOnLuyenID) o;
        return Objects.equals(idCauHoi, that.idCauHoi) && Objects.equals(idOnLuyen, that.idOnLuyen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCauHoi, idOnLuyen);
    }
}
