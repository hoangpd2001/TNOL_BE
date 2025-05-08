package com.learn.start.controller;

import com.learn.start.entity.Sach;
import com.learn.start.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/protected")
public class SachController {
    @Autowired
    private SachService sachService;


    @GetMapping
    public String protectedApi() {
        return "Bạn đã truy cập thành công!";
    }
}
