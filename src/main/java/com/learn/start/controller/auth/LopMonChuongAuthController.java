package com.learn.start.controller.auth;
import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.response.DeThiDTO_Res;
import com.learn.start.response.Res;
import com.learn.start.service.ChuongService;
import com.learn.start.service.DeThiService;
import com.learn.start.service.LopService;
import com.learn.start.service.MonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class LopMonChuongAuthController {
    @Autowired
    LopService lopService;
    @Autowired
    MonService monService;
    @Autowired
    ChuongService chuongService;
    @GetMapping("/lop")
    Res getAllLop(){
        Res res = new Res(true, MessageConstrains.SUCCESS, lopService.getAllLop());
        return res;
    }
    @GetMapping("/mon")
    Res getAllMon(){
        Res res = new Res(true, MessageConstrains.SUCCESS, monService.getAllMon());
        return res;
    }
    @GetMapping("mon/by_lop/{id}")
    Res getMonByLop(@PathVariable int id){
        Res res = new Res(true, MessageConstrains.SUCCESS, monService.getMonByLop(id));
        return res;
    }
    @GetMapping("/chuong")
    Res getAllChuong(){
        Res res = new Res(true, MessageConstrains.SUCCESS, chuongService.getAllChuong());
        return res;
    }
    @GetMapping("chuong/by-mon/{idMon}")
    public Res getByMon(@PathVariable int idMon) {

        Res res = new Res(true, MessageConstrains.SUCCESS, chuongService.getChuongByMon(idMon));
        return res;
    }
}
