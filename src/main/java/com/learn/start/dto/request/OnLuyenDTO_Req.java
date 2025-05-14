package com.learn.start.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OnLuyenDTO_Req {

    @NotNull(message = "ID đề thi không được để trống")
    private Integer idDeThi;

    @NotNull(message = "ID người dùng không được để trống")
    private Integer idUser;

    @NotNull(message = "Thời gian bắt đầu không được để trống")
    private LocalDateTime thoiGianBatDau;

    private LocalDateTime thoiGianKetThuc;

    private Integer trangThai;

    public OnLuyenDTO_Req() {
    }

    public Integer getIdDeThi() {
        return idDeThi;
    }

    public void setIdDeThi(Integer idDeThi) {
        this.idDeThi = idDeThi;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
}
