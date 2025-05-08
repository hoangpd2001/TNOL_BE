package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.MonDTO_Req;
import com.learn.start.dto.response.MonDTO_Res;
import com.learn.start.entity.Mon;
import com.learn.start.response.Res;
import com.learn.start.service.MonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mon")
public class MonController {
    @Autowired
    private MonService monService;
    @GetMapping
    Res getAllMon(){
        Res res = new Res(true, MessageConstrains.SUCCESS, monService.getAllMon());
      return res;
    }
    @GetMapping("/{id}")
    Res getMonById(@PathVariable int id){
        Optional<MonDTO_Res> mon = monService.getMonById(id);
        Res res;
        if(mon.isPresent()){
            res = new Res(true, MessageConstrains.SUCCESS, mon.get());
        }else{
            res = new Res(false, MessageConstrains.NOT_FOUND, null);
        }
        return res;
    }
    @GetMapping("/by_lop/{id}")
    Res getMonByLop(@PathVariable int id){
        Res res = new Res(true, MessageConstrains.SUCCESS, monService.getMonByLop(id));
        return res;
    }
    @PostMapping
    public ResponseEntity<Res<?>> addMon(@RequestBody @Valid MonDTO_Req mon) {
        MonDTO_Res monRes = monService.addMon(mon);
        if (monRes != null) {
            return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS, monRes));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Res<>(false, MessageConstrains.ERROR, null));
    }

    @PutMapping
    public ResponseEntity<Res<?>> updateMon(@RequestBody @Valid MonDTO_Req mon){
        MonDTO_Res monRes = monService.updateMon(mon);
        if(monRes != null){
            return ResponseEntity.ok(new Res<>(true, MessageConstrains.SUCCESS, monRes));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Res<>(false, MessageConstrains.ERROR, null));
    }
    @DeleteMapping("/{id}")
    Optional<Res> deleteMon(@PathVariable int id){
        Optional<MonDTO_Res> monRes = monService.deleteMon(id);
        if(monRes != null){
            return Optional.of(new Res(true, MessageConstrains.SUCCESS, monRes));
        }else{
            return Optional.of(new Res(false, MessageConstrains.NOT_FOUND, null));
        }
    }
}
