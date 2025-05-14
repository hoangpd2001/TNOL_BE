package com.learn.start.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ONLUYEN")
public class OnLuyen {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDDETHI", referencedColumnName = "ID")
    private DeThi deThi;

    @ManyToOne
    @JoinColumn(name = "IDUSERS", referencedColumnName = "ID")
    private Users users;

    @Column(name = "THOIGIANBATDAU")
    private LocalDateTime thoiGianBatDau;

    @Column(name = "THOIGIANKETTHUC")
    private LocalDateTime thoiGianKetThuc;

    @Column(name = "TRANGTHAI")
    private Integer trangThai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DeThi getDeThi() {
        return deThi;
    }

    public void setDeThi(DeThi deThi) {
        this.deThi = deThi;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public LocalDateTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public OnLuyen() {
    }

    public OnLuyen(Integer id) {
        this.id = id;
    }
// Getter và Setter
}
