package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.GiaoVienDTO_Req;
import com.learn.start.dto.response.ChuongDTO_Res;
import com.learn.start.dto.response.GiaoVienDTO_Res;
import com.learn.start.entity.GiaoVien;
import com.learn.start.response.Res;
import com.learn.start.service.GiaoVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/giaovien")
public class GiaoVienController {
    @Autowired
    private GiaoVienService giaoVienService;

    @GetMapping
    public ResponseEntity<Res<?>> list() {
        return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS,giaoVienService.getGiaoVien()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Res<?>> list(@PathVariable int id) {

        GiaoVienDTO_Res res = giaoVienService.getGiaoVienById(id);
        if(res == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Res<>(false, MessageConstrains.NOT_FOUND,null));
        }
        return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS,res));
    }
//    @PostMapping
//    public ResponseEntity<Res<?>> save(@ModelAttribute GiaoVienDTO_Req req) {
//        if(req.getId() != null)return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new Res<>(false, MessageConstrains.ERROR, null));
//        GiaoVienDTO_Res giaoVienRes = giaoVienService.CreateGiaoVien(req);
//        if (giaoVienRes != null) {
//            return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS, giaoVienRes));
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new Res<>(false, MessageConstrains.ERROR, null));
//    }
    @PutMapping
    public ResponseEntity<Res<?>> update(@RequestBody GiaoVienDTO_Req req) {
        if(req.getId() == null)return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Res<>(false, MessageConstrains.ERROR, null));
        GiaoVienDTO_Res giaoVienRes = giaoVienService.UpdateGiaoVien(req);
        if (giaoVienRes != null) {
            return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS, giaoVienRes));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Res<>(false, MessageConstrains.ERROR, null));
    }
//    @DeleteMapping
//    public ResponseEntity<Res<?>> delete(@RequestBody GiaoVienDTO_Req req) {}
}
