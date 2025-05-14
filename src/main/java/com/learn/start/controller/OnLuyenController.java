package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.KQOnLuyenDTO_Req;
import com.learn.start.dto.response.DeThiDTO_Res;
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
       String x=  onLuyenService.save(req);
        if(!Objects.equals(x, "1") && !Objects.equals(x, "2")) {
            return new Res<>(true, MessageConstrains.SUCCESS, x);
        }
        else {
            if(Objects.equals(x, "1")) {
                return new Res<>(false, MessageConstrains.ERROR, "Chỉ được nộp bài 1 lần");
            }
            return new Res<>(false, MessageConstrains.ERROR, "Đã hết thời gian nộp bài");
        }
    }

}
