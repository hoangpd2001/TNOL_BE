package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.ChuongDTO_Req;
import com.learn.start.dto.response.ChuongDTO_Res;
import com.learn.start.response.Res;
import com.learn.start.service.ChuongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chuong")
public class ChuongController {
    @Autowired
    private ChuongService chuongService;
    @GetMapping
    Res getAllChuong(){
        Res res = new Res(true, MessageConstrains.SUCCESS, chuongService.getAllChuong());
        return res;
    }
    @GetMapping("/{id}")
    Res getChuongById(@PathVariable int id){
        Optional<ChuongDTO_Res> chuong = chuongService.getChuongById(id);
        Res res;
        if(chuong.isPresent()){
            res = new Res(true, MessageConstrains.SUCCESS, chuong.get());
        }else{
            res = new Res(false, MessageConstrains.NOT_FOUND, null);
        }
        return res;
    }
    @GetMapping("/by-mon/{idMon}")
    public Res getByMon(@PathVariable int idMon) {

        Res res = new Res(true, MessageConstrains.SUCCESS, chuongService.getChuongByMon(idMon));
        return res;
    }
    @PostMapping
    public ResponseEntity<Res<?>> addChuong(@RequestBody @Valid ChuongDTO_Req chuong) {
        ChuongDTO_Res chuongRes = chuongService.addChuong(chuong);
        if (chuongRes != null) {
            return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS, chuongRes));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Res<>(false, MessageConstrains.ERROR, null));
    }

    @PutMapping
    public ResponseEntity<Res<?>> updateChuong(@RequestBody @Valid ChuongDTO_Req chuong){
        ChuongDTO_Res chuongRes = chuongService.updateChuong(chuong);
        if(chuongRes != null){
            return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS, chuongRes));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Res<>(false, MessageConstrains.ERROR, null));
    }
    @DeleteMapping("/{id}")
    Optional<Res> deleteChuong(@PathVariable int id){
        Optional<ChuongDTO_Res> chuongRes = chuongService.deleteChuong(id);
        if(chuongRes != null){
            return Optional.of(new Res(true, MessageConstrains.SUCCESS, chuongRes));
        }else{
            return Optional.of(new Res(false, MessageConstrains.NOT_FOUND, null));
        }
    }
}
