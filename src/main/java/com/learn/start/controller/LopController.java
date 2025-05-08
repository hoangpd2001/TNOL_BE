package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.LopDTO_Req;
import com.learn.start.dto.response.LopDTO_Res;
import com.learn.start.response.Res;
import com.learn.start.service.LopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lop")
public class LopController {
    @Autowired
    private LopService lopService;
    @Autowired
    private HttpMessageConverters messageConverters;

    @GetMapping
    Res getAllLop(){
        Res res = new Res(true, MessageConstrains.SUCCESS, lopService.getAllLop());
        return res;
    }

    @GetMapping("/{id}")
    Res getLopById(@PathVariable int id){
        Optional<LopDTO_Res> lop = lopService.getLopById(id);
        if(lop.isPresent()){
            Res res = new Res(true, MessageConstrains.SUCCESS, lop.get());
            return res;
        }else{
            return new Res(false, MessageConstrains.NOT_FOUND, null);
        }
    }
    @PostMapping
    Res addLop(@RequestBody @Valid LopDTO_Req lop){
        LopDTO_Res lopDTORes = lopService.save(lop);
        Res res;
        if(lopDTORes != null){
            res = new Res(true, MessageConstrains.SUCCESS, lopDTORes);
        }else{
            res = new Res(false, MessageConstrains.NOT_FOUND, lopDTORes);
        }
        return res;
    }
    @PutMapping
    Res updateLop(@RequestBody @Valid LopDTO_Req lop){
        LopDTO_Res lopDTORes = lopService.update(lop);
        Res res;
        if(lopDTORes != null){
            res = new Res(true, MessageConstrains.SUCCESS, lopDTORes);
        }else{
            res = new Res(false, MessageConstrains.NOT_FOUND, lopDTORes);
        }
        return res;
    }
    @DeleteMapping("/{id}")
        Res deleteLop(@PathVariable int id){
        Optional<LopDTO_Res> lop = lopService.delete(id);
        if(lop.isPresent()){
            Res res = new Res(true, MessageConstrains.SUCCESS, lop.get());
            return res;
        }else{
            return new Res(false, MessageConstrains.NOT_FOUND, null);
        }
    }
}
