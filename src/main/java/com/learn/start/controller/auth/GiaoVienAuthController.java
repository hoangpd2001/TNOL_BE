package com.learn.start.controller.auth;
import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.GiaoVienDTO_Req;
import com.learn.start.dto.response.ChuongDTO_Res;
import com.learn.start.dto.response.GiaoVienDTO_Res;
import com.learn.start.entity.GiaoVien;
import com.learn.start.response.Res;
import com.learn.start.security.CustomUserDetails;
import com.learn.start.service.GiaoVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/giaovien")
public class GiaoVienAuthController {
    @Autowired
    private GiaoVienService giaoVienService;

    @GetMapping
    public ResponseEntity<Res<?>> list() {
        return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS,giaoVienService.getGiaoVien()));
    }
    @PostMapping
    public ResponseEntity<Res<?>> save(@ModelAttribute GiaoVienDTO_Req req) throws ParseException {
        if(req.getId() != null)return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Res<>(false, MessageConstrains.ERROR, null));
        GiaoVienDTO_Res giaoVienRes = giaoVienService.CreateGiaoVien(req);
        if (giaoVienRes != null) {
            return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS, giaoVienRes));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Res<>(false, MessageConstrains.ERROR, null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Res<?>> giaoVieninfo(@PathVariable int id) {
        GiaoVienDTO_Res res = giaoVienService.getGiaoVienById2(id);
        if(res == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Res<>(false, MessageConstrains.NOT_FOUND,null));
        }
        return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS,res));
    }
}
