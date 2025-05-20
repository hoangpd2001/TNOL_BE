package com.learn.start.controller;

import com.learn.start.dto.response.DeThiDTO_Res;
import com.learn.start.dto.response.UsersDTO_Res;
import com.learn.start.entity.MuaDeThi;
import com.learn.start.response.Res;
import com.learn.start.security.CustomUserDetails;
import com.learn.start.service.MuaDeThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/muadethi")
public class MuaDeThiController {

    @Autowired
    private MuaDeThiService muaDeThiService;

    @GetMapping("/check/{idDeThi}")
    public Res<Boolean> checkMua(@PathVariable  Integer idDeThi) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer idUser = userDetails.getId();
        System.out.println(idUser + " " + idDeThi);
        boolean result = muaDeThiService.checkMuaDeThi(idUser, idDeThi);
        return new Res<>(true, "Kiểm tra mua thành công", result);
    }

    @PostMapping
    public Res<MuaDeThi> creatMuaDeThi(@RequestParam int idDeThi) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int idUser = userDetails.getId();
        MuaDeThi result = muaDeThiService.creatMuaDeThi(idUser, idDeThi);
        if (result == null) {
            return new Res<>(false, "Tạo thất bại hoặc đã mua rồi", null);
        }
        return new Res<>(true, "Tạo mua đề thi thành công", result);
    }

    @GetMapping("/by-user")
    public ResponseEntity<Res<List<DeThiDTO_Res>>> getDeThiByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int idUser = userDetails.getId();
        List<DeThiDTO_Res> list = muaDeThiService.getMuaDeThiByUser(idUser);
        return ResponseEntity.ok(new Res<>(true, "Lấy danh sách đề thi đã mua theo user thành công", list));
    }

    @GetMapping("/by-dethi")
    public ResponseEntity<Res<List<UsersDTO_Res>>> getUsersByDeThi(@RequestParam int idDeThi) {
        List<UsersDTO_Res> list = muaDeThiService.getMuaDeThibyDeThi(idDeThi);
        return ResponseEntity.ok(new Res<>(true, "Lấy danh sách user đã mua đề thi thành công", list));
    }
}
