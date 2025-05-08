package com.learn.start.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "MON")
public class Mon {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TENMON")
    private String tenMon;
    @Column(name = "IDLOP")
    private int idLop;
    public Mon() {
    }

    public Mon(int id, String tenMon, int idLop) {
        this.id = id;
        this.tenMon = tenMon;
        this.idLop = idLop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getIdLop() {
        return idLop;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }


}
