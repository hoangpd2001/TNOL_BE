package com.learn.start.dto.response;

import jakarta.validation.constraints.NotBlank;

public class ChuongDTO_Res {
    private int id;
    private String tenChuong;
    private int idMon;

    public ChuongDTO_Res() {
    }

    public ChuongDTO_Res(int id, String tenChuong, int idMon) {
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
