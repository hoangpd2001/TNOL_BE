package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.KQOnLuyenDTO_Req;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.dto.response.DeThiDTO_Res;
import com.learn.start.dto.response.OnLuyenDTO_Res;
import com.learn.start.response.Res;
import com.learn.start.security.CustomUserDetails;
import com.learn.start.service.OnLuyenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/onluyen")
public class OnLuyenController {
    @Autowired()
    private OnLuyenService onLuyenService;
    @Autowired
    private MessageSource messageSource;


    @GetMapping("/check/{id}")
    public Res<?> getById(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int idUser = userDetails.getId();
        System.out.println(idUser);
        Object x=  onLuyenService.check(idUser, id);
            if(x != null) {
                return new Res<>(true, MessageConstrains.SUCCESS, x);
            }
            else {return new Res<>(false, MessageConstrains.ERROR, null);}
    }
    @PostMapping("/save")
    public Res<?> getById(@RequestBody @Valid List<KQOnLuyenDTO_Req> req) {
       List<CauHoiDTO_Res> listRes =  onLuyenService.save(req);
        if(listRes != null) {
            return new Res<>(true, MessageConstrains.SUCCESS, listRes);
        }
        else {
            return new Res<>(false, "Bài đã được nộp / Đã hết thời hạn nộp", null);
        }
    }
    @GetMapping("/user/filter")
    public Res<?> getbyUser( @RequestParam(required = false) Integer lop,
                                             @RequestParam(required = false) Integer mon,
                                             @RequestParam(required = false) Integer chuong
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int id = userDetails.getId();
        List<OnLuyenDTO_Res> res = onLuyenService.getbyUsers(lop, mon, chuong,id);
        if(res != null) {
            return new Res<>(true, MessageConstrains.SUCCESS, res);
        }
        else{
            return new Res<>(false, MessageConstrains.ERROR, null);
        }
    }
    @GetMapping("/getketqua/{idOnLuyen}")
    public Res<?> getKQById(@PathVariable Integer idOnLuyen) {

        List<CauHoiDTO_Res> x=  onLuyenService.getKQOnLuyen(idOnLuyen);
        if(x != null) {
            return new Res<>(true, MessageConstrains.SUCCESS, x);
        }
        else {return new Res<>(false, MessageConstrains.ERROR, null);}
    }
}
