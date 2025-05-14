package com.learn.start.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.CauHoiDTO_Req;
import com.learn.start.dto.request.CauHoiDeThiDTO_Req;
import com.learn.start.dto.request.DeThiDTO_Req;
import com.learn.start.dto.request.DeThiRequest;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.dto.response.CauHoiDeThiDTO_Res;
import com.learn.start.response.Res;
import com.learn.start.service.CauHoiDeThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cauhoidethi")
public class CauHoiDeThiController {

    @Autowired
    private CauHoiDeThiService service;
    @Autowired
    private CauHoiDeThiService cauHoiDeThiService;

    @GetMapping
    public Optional<CauHoiDeThiDTO_Res> getById(@RequestParam int idCauHoi, @RequestParam int idDeThi) {
        return service.getById(idCauHoi, idDeThi);
    }
    @GetMapping("/dethi/{idDeThi}")
    public Res<List<CauHoiDTO_Res>> getByIdDeThi(@PathVariable int idDeThi) {
        List<CauHoiDTO_Res> res=  service.getByIdDeThi(idDeThi);
        if(!res.isEmpty()){
            return new Res<>(true, MessageConstrains.SUCCESS, res);
        }
        return new Res<>(false, MessageConstrains.NOT_FOUND,null);
    }
    @PostMapping
    public Res<?> create(@RequestBody DeThiRequest req) {
        int res = service.create(req);
        if(res > 0) {
                return new Res<>(true, MessageConstrains.SUCCESS,res);
        }
        return new Res<>(false, MessageConstrains.NOT_FOUND,null);
    }
    @PostMapping("/import")
    public ResponseEntity<?> importCauHoi(
            @RequestPart("cauHoiListJson") String cauHoiListJson,
            @RequestPart("deThiJson") String deThiJson,
            @RequestPart(name = "hinhAnhList", required = false) List<MultipartFile> hinhAnh,
            @RequestPart(name = "hinhAnhDaList", required = false) List<MultipartFile> hinhAnhDa
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CauHoiDTO_Req> list = objectMapper.readValue(cauHoiListJson,
                new TypeReference<List<CauHoiDTO_Req>>() {});
        DeThiDTO_Req deThi = objectMapper.readValue(deThiJson, DeThiDTO_Req.class);
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
        Integer res = cauHoiDeThiService.importDeThi(deThi,list);
        if(res > 0 ) {
            System.out.println(res);
            return ResponseEntity.ok(new Res(true, MessageConstrains.SUCCESS, res));

        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Res<>(false, MessageConstrains.ERROR, null));
        }
    }
    @DeleteMapping
    public void delete(@RequestParam int idCauHoi, @RequestParam int idDeThi) {
        service.delete(idCauHoi, idDeThi);
    }
}
