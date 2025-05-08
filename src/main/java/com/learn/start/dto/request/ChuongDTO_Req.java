package com.learn.start.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChuongDTO_Req {
    private int id;
    @NotBlank(message = "Tên chương không được để trống")
    private String tenChuong;
    @NotNull(message = "idMon không được để trống")
    private Integer idMon;

    public ChuongDTO_Req() {
    }

    public ChuongDTO_Req(int id, String tenChuong, int idMon) {
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
