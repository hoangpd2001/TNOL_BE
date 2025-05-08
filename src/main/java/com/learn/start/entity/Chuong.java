package com.learn.start.entity;

import jakarta.persistence.*;


@Entity
@Table(name="CHUONG")
public class Chuong {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="TENCHUONG")
    private String tenChuong;
    @Column(name="IDMON")
    private int idMon;

    public Chuong() {
    }

    public Chuong(int id) {
        this.id = id;
    }

    public Chuong(int id, String tenChuong, int idMon) {
        this.id = id;
        this.tenChuong = tenChuong;
        this.idMon = idMon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public int getIdMon() {
        return idMon;
    }

    public void setIdMon(int idMon) {
        this.idMon = idMon;
    }
}
