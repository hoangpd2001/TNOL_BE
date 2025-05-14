package com.learn.start.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CAUHOI_DETHI")
public class CauHoiDeThi {

    @EmbeddedId
    private CauHoiDeThiId id;

    @ManyToOne
    @MapsId("idCauHoi")
    @JoinColumn(name = "IDCAUHOI")
    private CauHoi cauHoi;

    @ManyToOne
    @MapsId("idDeThi")
    @JoinColumn(name = "IDDETHI")
    private DeThi deThi;

    public CauHoiDeThi() {}

    public CauHoiDeThi(CauHoi cauHoi, DeThi deThi) {
        this.id = new CauHoiDeThiId(cauHoi.getId(), deThi.getId());
        this.cauHoi = cauHoi;
        this.deThi = deThi;
    }

    public CauHoiDeThiId getId() {
        return id;
    }

    public void setId(CauHoiDeThiId id) {
        this.id = id;
    }

    public CauHoi getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(CauHoi cauHoi) {
        this.cauHoi = cauHoi;
    }

    public DeThi getDeThi() {
        return deThi;
    }

    public void setDeThi(DeThi deThi) {
        this.deThi = deThi;
    }
}
