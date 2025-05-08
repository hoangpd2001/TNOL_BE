package com.learn.start.dto.response;

public class MonDTO_Res {
    private int id;
    private String tenMon;
    private int idLop;

    public MonDTO_Res(int id, String tenMon, int idLop) {
        this.id = id;
        this.tenMon = tenMon;
        this.idLop = idLop;
    }

    public int getIdLop() {
        return idLop;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }

    public MonDTO_Res() {
    }

    public MonDTO_Res(int id, String tenMon) {
        this.id = id;
        this.tenMon = tenMon;
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
}
