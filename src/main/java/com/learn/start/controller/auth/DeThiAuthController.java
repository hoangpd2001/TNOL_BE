package com.learn.start.controller.auth;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.KQOnLuyenDTO_Req;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.dto.response.DeThiDTO_Res;
import com.learn.start.response.Res;
import com.learn.start.service.CauHoiDeThiService;
import com.learn.start.service.DeThiService;
import com.learn.start.service.OnLuyenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class DeThiAuthController {
    @Autowired
    private DeThiService deThiService;
    @Autowired
    private CauHoiDeThiService cauHoiDeThiService;
    @Autowired
    OnLuyenService onLuyenService;
    @GetMapping("/dethi")
    public Res<List<DeThiDTO_Res>> getAll() {
        return new Res<>(true, MessageConstrains.SUCCESS, deThiService.getAll());
    }
    @GetMapping("/dethi/{id}")
    public Res<DeThiDTO_Res> getById(@PathVariable Integer id) {

        DeThiDTO_Res res =  deThiService.getById(id);
        if(res == null){
            return new Res<>(false, MessageConstrains.NOT_FOUND, null);
        }
        return new Res<>(true, MessageConstrains.SUCCESS, res);

    }
    @GetMapping("/cauhoi/dethi/{idDeThi}")
    public Res<List<CauHoiDTO_Res>> getByIdDeThi(@PathVariable int idDeThi) {
        List<CauHoiDTO_Res> res=  cauHoiDeThiService.getByIdDeThi(idDeThi);
        if(!res.isEmpty()){
            return new Res<>(true, MessageConstrains.SUCCESS, res);
        }
        return new Res<>(false, MessageConstrains.NOT_FOUND,null);
    }
    @PostMapping("/onluyen/check")
    public Res<?> getById(@RequestBody @Valid List<KQOnLuyenDTO_Req> req) {
        List<CauHoiDTO_Res> res=  onLuyenService.checkNotLogin(req);
        if(!res.isEmpty()){
            return new Res<>(true, MessageConstrains.SUCCESS, res);
        }
        return new Res<>(false, MessageConstrains.NOT_FOUND,null);
    }
}
