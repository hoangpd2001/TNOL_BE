package com.learn.start.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/import2")
    public ResponseEntity<?> importCauHoi(
            @RequestPart("cauHoiListJson") String cauHoiListJson,
            @RequestPart(name = "hinhAnhList", required = false) List<MultipartFile> hinhAnh,
            @RequestPart(name = "hinhAnhDaList", required = false) List<MultipartFile> hinhAnhDa
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CauHoiDTO_Req> list = objectMapper.readValue(cauHoiListJson,
                new TypeReference<List<CauHoiDTO_Req>>() {});
        if (hinhAnh != null) {
            System.out.println("hinh anh : " + hinhAnh.size());
            for (int i = 0; i < hinhAnh.size(); i++) {
                MultipartFile file = hinhAnh.get(i);
                String originalFileName = file.getOriginalFilename();
                String stt = originalFileName.split("\\.")[0];
                int sttNumber = Integer.parseInt(stt);
                list.get(sttNumber -1 ).setHinhAnh(file);
            }
        }else{

        }
        if (hinhAnhDa != null) {
            for (int i = 0; i < hinhAnhDa.size(); i++) {
                MultipartFile file = hinhAnhDa.get(i);
                String originalFileName = file.getOriginalFilename();
                String stt = originalFileName.split("\\.")[0];
                int sttNumber = Integer.parseInt(stt);
                list.get(sttNumber -1 ).setHinhAnhDa(file);
            }
        }
        Integer res = cauHoiService.importQuestionsImage(list);
        if(res > 0 ) {
            System.out.println(res);
            return ResponseEntity.ok(new Res(true, MessageConstrains.SUCCESS, res));

            }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Res<>(false, MessageConstrains.ERROR, null));
        }
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
