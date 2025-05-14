package com.learn.start.dto.request;

import java.util.List;
import jakarta.validation.constraints.NotNull;
public class DeThiRequest {
    @NotNull(message = "de thi không được để trống")
    private DeThiDTO_Req deThi;
    @NotNull(message = "cau hoi không được để trống")
    private List<CauHoiDeThiDTO_Req> cauHoiDeThis;

    public List<CauHoiDeThiDTO_Req> getCauHoiDeThis() {
        return cauHoiDeThis;
    }

    public void setCauHoiDeThis(List<CauHoiDeThiDTO_Req> cauHoiDeThis) {
        this.cauHoiDeThis = cauHoiDeThis;
    }

    public DeThiDTO_Req getDeThi() {
        return deThi;
    }

    public void setDeThi(DeThiDTO_Req deThi) {
        this.deThi = deThi;
    }
}
