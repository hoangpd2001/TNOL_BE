package com.learn.start.controller;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.CauHoiDTO_Req;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.entity.CauHoi;
import com.learn.start.response.Res;
import com.learn.start.service.CauHoiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cauhoi")
public class CauHoiController {

    @Autowired
    private CauHoiService cauHoiService;
    @Autowired
    private HttpMessageConverters messageConverters;

    @GetMapping
    public ResponseEntity<Res<?>> getAllCauHoi() {
        return ResponseEntity.ok(new Res(true, MessageConstrains.SUCCESS,cauHoiService.getAllCauHoi())) ;
    }
    @GetMapping("/filter")
    public ResponseEntity<Res<?>> filterCauHoi(
            @RequestParam int giaovien,
            @RequestParam int chuong,
            @RequestParam(required = false) Integer mucdo
    ) {
        List<CauHoiDTO_Res> result = cauHoiService.filterCauHoi(giaovien, chuong, mucdo);
        return ResponseEntity.ok(new Res(true,MessageConstrains.SUCCESS, result));
    }
    @PostMapping
    public ResponseEntity<CauHoi> createCauHoi(@Valid @RequestBody CauHoiDTO_Req req) {
        CauHoi created = cauHoiService.createCauHoi(req);
        return ResponseEntity.ok(created);
    }
    @PostMapping("/import")
    public ResponseEntity<Res<?>> importCauHoi(@RequestBody List<CauHoiDTO_Req> questionList) {
        try {
            List<CauHoiDTO_Res> res = cauHoiService.importQuestions(questionList);
            return ResponseEntity.ok(new Res(true, MessageConstrains.SUCCESS,res ));
        } catch (Exception e) {
            e.printStackTrace(); // log lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Res<>(false, MessageConstrains.ERROR, null));        }
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<CauHoi> getCauHoiById(@PathVariable int id) {
//        return cauHoiService.getById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCauHoi(@PathVariable int id) {
//        boolean deleted = cauHoiService.deleteCauHoi(id);
//        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
//    }
}
