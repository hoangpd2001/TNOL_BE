package com.learn.start.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MonDTO_Req {

    private int id;
    @NotBlank(message = "Tên Môn học không được để trống")
    private String tenMon;
    @NotNull(message = "Id Lớp học không được để trống")
    private Integer idLop;
    public MonDTO_Req(int id, String tenMon, int idLop) {
        this.id = id;
        this.tenMon = tenMon;
        this.idLop = idLop;
    }
    public MonDTO_Req() {
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

    public int getIdLop() {
        return idLop;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }
}
