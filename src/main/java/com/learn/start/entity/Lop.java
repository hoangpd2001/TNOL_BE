package com.learn.start.entity;

import jakarta.persistence.*;

@Entity
@Table(name="LOP")
public class Lop {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column(name="TENLOP")
    private String tenLop;

    public Lop() {
    }

    public Lop(int ID, String tenLop) {
        this.ID = ID;
        this.tenLop = tenLop;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
