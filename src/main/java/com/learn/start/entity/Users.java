package com.learn.start.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
      @Column(name = "HOTEN")
    private String hoTen;
      @Column(name = "NGAYSINH")
    private Date ngaySinh;
      @Column(name = "TRUONG")
    private String truong;
      @Column(name = "SDT")
    private String sdt;
      @Column(name = "GMAIL")
    private String gmail;
      @Column(name = "MATKHAU")
    private String mk;
      @Column(name = "ANH")
    private String anh;
    @ManyToOne
    @JoinColumn(name = "IDROLE", referencedColumnName = "ID")
    private Role role;
    @Column(name = "SODU")
    private int soDu;

    public Users(Integer id) {
        this.id = id;
    }

    public Users() {
    }

    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTruong() {
        return truong;
    }

    public void setTruong(String truong) {
        this.truong = truong;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
