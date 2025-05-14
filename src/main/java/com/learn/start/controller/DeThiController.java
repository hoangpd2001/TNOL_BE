package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.DeThiDTO_Req;
import com.learn.start.dto.response.DeThiDTO_Res;
import com.learn.start.response.Res;
import com.learn.start.security.CustomUserDetails;
import com.learn.start.service.DeThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dethi")
public class DeThiController {

    @Autowired
    private DeThiService deThiService;

    @GetMapping
    public Res<List<DeThiDTO_Res>> getAll() {
        return new Res<>(true, MessageConstrains.SUCCESS, deThiService.getAll());
    }
    @GetMapping("/giaovien")
    public List<DeThiDTO_Res> getAllbyGiaovien() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int id = userDetails.getId();
        return deThiService.getAllbyGiaoVien(id);
    }
    @GetMapping("/giaovien/filter")
    public List<DeThiDTO_Res> getbyGiaovien( @RequestParam(required = false) Integer lop,
                                             @RequestParam(required = false) Integer mon,
                                             @RequestParam(required = false) Integer chuong
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int id = userDetails.getId();
        return deThiService.getbyGiaoVien(lop, mon, chuong,id);
    }
    @GetMapping("/{id}")
    public DeThiDTO_Res getById(@PathVariable Integer id) {
        return deThiService.getById(id);
    }


    @PostMapping
    public DeThiDTO_Res create(@RequestBody DeThiDTO_Req dto) {
        return deThiService.create(dto);
    }

    @PutMapping("/{id}")
    public DeThiDTO_Res update(@PathVariable Integer id, @RequestBody DeThiDTO_Req dto) {
        return deThiService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        deThiService.delete(id);
    }
}
