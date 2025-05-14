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
    @ManyToOne
    @JoinColumn(name = "IDLOP", referencedColumnName = "ID")
    private Lop lop;
    public Mon() {
    }

    public Mon(int id) {
        this.id = id;
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

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        lop = lop;
    }
}
