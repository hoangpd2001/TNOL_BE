package com.learn.start.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "MUADETHI")
public class MuaDeThi {
    @EmbeddedId
    private MuaDeThiID id;

    @ManyToOne
    @MapsId("idUsers")
    @JoinColumn(name = "IDUSERS")
    private Users users;

    @ManyToOne
    @MapsId("idDeThi")
    @JoinColumn(name = "IDDETHI")
    private DeThi deThi;


    @Column(name="NGAYMUA")
    private Timestamp createDate;
    @Column(name="SOTIEN")
    private Long soTien;

    public MuaDeThi() {
    }

    public MuaDeThi(MuaDeThiID id, Users users, DeThi deThi, Timestamp createDate, Long soTien) {
        this.id = id;
        this.users = users;
        this.deThi = deThi;
        this.createDate = createDate;
        this.soTien = soTien;
    }

    public MuaDeThiID getId() {
        return id;
    }

    public void setId(MuaDeThiID id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public DeThi getDeThi() {
        return deThi;
    }

    public void setDeThi(DeThi deThi) {
        this.deThi = deThi;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Long getSoTien() {
        return soTien;
    }

    public void setSoTien(Long soTien) {
        this.soTien = soTien;
    }
}
