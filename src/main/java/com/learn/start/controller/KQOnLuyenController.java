package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.ChuongDTO_Req;
import com.learn.start.dto.request.KQOnLuyenDTO_Req;
import com.learn.start.response.Res;
import com.learn.start.service.KQOnLuyenService;
import com.learn.start.service.OnLuyenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kqonluyen")
public class KQOnLuyenController {

    @Autowired
    private OnLuyenService onLuyenService;
    @Autowired
    private KQOnLuyenService kqOnLuyenService;
    @PostMapping
    public Res<?> addkq(@RequestBody @Valid KQOnLuyenDTO_Req req) {

            return new Res<>(true, MessageConstrains.SUCCESS,kqOnLuyenService.addKQ(req));

    }

}
