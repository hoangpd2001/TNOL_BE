package com.learn.start.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "KQONLUYEN")
public class KQOnLuyen {
    @EmbeddedId
    private KQOnLuyenID id;

    public KQOnLuyenID getId() {
        return id;
    }

    public void setId(KQOnLuyenID id) {
        this.id = id;
    }

    @ManyToOne
    @MapsId("idCauHoi")
    @JoinColumn(name = "IDCAUHOI")
    private CauHoi cauHoi;

    @ManyToOne
    @MapsId("idOnLuyen")
    @JoinColumn(name = "IDONLUYEN")
    private OnLuyen onLuyen;

    @Column(name = "DAPAN")
    private String dapan;

    public KQOnLuyen() {
    }

    public CauHoi getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(CauHoi cauHoi) {
        this.cauHoi = cauHoi;
    }

    public OnLuyen getOnLuyen() {
        return onLuyen;
    }

    public void setOnLuyen(OnLuyen onLuyen) {
        this.onLuyen = onLuyen;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }
}
