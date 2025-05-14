package com.learn.start.entity;

import jakarta.persistence.*;

@Entity
@Table(name="LOP")
public class Lop {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="TENLOP")
    private String tenLop;

    public Lop() {
    }

    public Lop(Integer ID) {
        this.id = ID;
    }

    public Lop(int ID, String tenLop) {
        this.id = ID;
        this.tenLop = tenLop;
    }

    public int getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
