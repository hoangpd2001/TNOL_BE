package com.learn.start.dto.request;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
public class LopDTO_Req {
    private int id;
    @NotBlank(message = "Tên lớp học không được để trống")
    @Size
    private String tenLop;

    public LopDTO_Req() {
    }

    public LopDTO_Req(int id, String tenLop) {
        this.id = id;
        this.tenLop = tenLop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
