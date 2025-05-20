package com.learn.start.entity;

import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.io.Serializable;

@Embeddable
public class MuaDeThiID {

    private Integer idUsers;
    private Integer idDeThi;

    public MuaDeThiID() {
    }

    public MuaDeThiID(Integer idUsers, Integer idDeThi) {
        this.idUsers = idUsers;
        this.idDeThi = idDeThi;
    }

    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
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
        if (!(o instanceof MuaDeThiID)) return false;
        MuaDeThiID that = (MuaDeThiID) o;
        return Objects.equals(idUsers, that.idUsers) && Objects.equals(idDeThi, that.idDeThi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsers, idDeThi);
    }
}
