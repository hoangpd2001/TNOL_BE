package com.learn.start.service;

import com.learn.start.dto.request.ChuongDTO_Req;
import com.learn.start.dto.response.ChuongDTO_Res;
import com.learn.start.entity.Chuong;
import com.learn.start.entity.Mon;
import com.learn.start.repository.ChuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChuongService {
    @Autowired
    private ChuongRepository chuongRepository;
    public List<ChuongDTO_Res> getAllChuong(){
        List<Chuong> list =  chuongRepository.findAll();
        List<ChuongDTO_Res> res = new ArrayList<ChuongDTO_Res>();
        for (Chuong chuong : list) {
            ChuongDTO_Res chuongDTO_Res = new ChuongDTO_Res();
            chuongDTO_Res.setId(chuong.getId());
            chuongDTO_Res.setTenChuong(chuong.getTenChuong());
            chuongDTO_Res.setIdMon(chuong.getMon().getId());
            res.add(chuongDTO_Res);
        }
        return res;
    }
    public List<ChuongDTO_Res> getChuongByMon(int idMon){
        List<Chuong> list = chuongRepository.findByMon_Id(idMon);
        List<ChuongDTO_Res> res = new ArrayList<>();
        for (Chuong chuong : list) {
            ChuongDTO_Res dto = new ChuongDTO_Res();
            dto.setId(chuong.getId());
            dto.setTenChuong(chuong.getTenChuong());
            dto.setIdMon(chuong.getMon().getId());
            res.add(dto);
        }
        return res;
    }

    public Optional<ChuongDTO_Res> getChuongById(int id) {
        Optional<Chuong> chuong = chuongRepository.findById(id);
        if(chuong.isPresent()) {
            ChuongDTO_Res chuongDTO_Res = new ChuongDTO_Res();
            chuongDTO_Res.setId(chuong.get().getId());
            chuongDTO_Res.setTenChuong(chuong.get().getTenChuong());
            chuongDTO_Res.setIdMon(chuong.get().getMon().getId());
            return Optional.of(chuongDTO_Res);
        }
        return Optional.empty();
    }
    public ChuongDTO_Res addChuong(ChuongDTO_Req chuongDTO_Req) {
        Chuong chuong = new Chuong();
        chuong.setTenChuong(chuongDTO_Req.getTenChuong());
        chuong.setMon(new Mon(chuongDTO_Req.getIdMon()));
        chuongRepository.save(chuong);
        ChuongDTO_Res chuongDTO_Res = new ChuongDTO_Res();
        chuongDTO_Res.setId(chuong.getId());
        chuongDTO_Res.setTenChuong(chuong.getTenChuong());
        return chuongDTO_Res;
    }
    public ChuongDTO_Res updateChuong(ChuongDTO_Req chuongDTO_Req) {
        Chuong chuong = new Chuong();
        chuong.setId(chuongDTO_Req.getId());
        chuong.setTenChuong(chuongDTO_Req.getTenChuong());
        chuong.setMon(new Mon(chuongDTO_Req.getIdMon()));
        chuongRepository.save(chuong);
        ChuongDTO_Res chuongDTO_Res = new ChuongDTO_Res();
        chuongDTO_Res.setId(chuong.getId());
        chuongDTO_Res.setTenChuong(chuong.getTenChuong());
        chuongDTO_Res.setIdMon(chuongDTO_Req.getIdMon());
        return chuongDTO_Res;
    }
    public Optional<ChuongDTO_Res> deleteChuong(int id) {
        Optional<Chuong> chuong = chuongRepository.findById(id);
        if(chuong.isPresent()) {
            chuongRepository.delete(chuong.get());
            ChuongDTO_Res chuongDTO_Res = new ChuongDTO_Res();
            chuongDTO_Res.setId(chuong.get().getId());
            chuongDTO_Res.setTenChuong(chuong.get().getTenChuong());
            chuongDTO_Res.setIdMon(chuong.get().getMon().getId());
            return Optional.of(chuongDTO_Res);
        }
        return Optional.empty();
    }
}
