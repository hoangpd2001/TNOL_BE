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
    @ManyToOne
    @JoinColumn(name = "IDMON", referencedColumnName = "ID")
    private Mon mon;

    public Chuong() {
    }

    public Chuong(int id) {
        this.id = id;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Mon getMon() {
        return mon;
    }

    public void setMon(Mon mon) {
        this.mon = mon;
    }
}
