package com.learn.start.service;

import com.learn.start.dto.request.CauHoiDTO_Req;
import com.learn.start.dto.request.KQOnLuyenDTO_Req;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.entity.DeThi;
import com.learn.start.entity.KQOnLuyen;
import com.learn.start.entity.OnLuyen;
import com.learn.start.entity.Users;
import com.learn.start.repository.OnLuyenRepository;
import jakarta.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map;
@Service
public class OnLuyenService {
    @Autowired
    OnLuyenRepository onLuyenRepository;
    @Autowired
    KQOnLuyenService kqOnLuyenService;


    public Object check(Integer idUser, Integer idDeThi) {
        Optional<OnLuyen> onLuyen = onLuyenRepository.findFirstByDeThi_IdAndUsers_IdAndThoiGianKetThucIsNullOrderByThoiGianBatDauDesc(idDeThi, idUser);
        if (onLuyen.isPresent()) {
            OnLuyen ol = onLuyen.get();
            int thoiGianLamPhut = ol.getDeThi().getThoiGianLam();
            LocalDateTime start = ol.getThoiGianBatDau();
            LocalDateTime now = LocalDateTime.now();

            Duration elapsed = Duration.between(start, now);
            long elapsedSeconds = elapsed.getSeconds();
            long totalAllowedSeconds = thoiGianLamPhut * 60;
            long remainingSeconds = totalAllowedSeconds - elapsedSeconds;

            if (remainingSeconds <= 0) {
               return  Map.of(
                        "thoigian", -1,
                        "id",  create(idDeThi,idUser)
                );
            }

            return Map.of(
                    "thoigian", remainingSeconds,
                    "id", ol.getId(),
                    "dapan",kqOnLuyenService.getKQ(ol.getId())
            );
        }
        return  Map.of(
                "thoigian", -1,
                "id",  create(idDeThi,idUser)
        );
    }

    public Long create(Integer idDeThi,Integer idUser){
        System.out.println("tao moi");
        OnLuyen ol = new OnLuyen();
        ol.setDeThi(new DeThi(idDeThi));
        ol.setThoiGianBatDau(LocalDateTime.now());
        ol.setUsers(new Users(idUser));
        ol.setTrangThai(1);
        ol = onLuyenRepository.save(ol);
        return  ol.getId().longValue();
    }
    public String save(List<KQOnLuyenDTO_Req> req){
        Integer id = req.getFirst().getIdOnLuyen();
        OnLuyen onluyen = onLuyenRepository.findById(id).orElse(null);
        if(onluyen == null || onluyen.getThoiGianKetThuc() != null){ return "1";}
        LocalDateTime start = onluyen.getThoiGianBatDau(); // bat dau
        LocalDateTime now = LocalDateTime.now(); // hien tai

        Duration elapsed = Duration.between(start, now);
        long elapsedSeconds = elapsed.getSeconds(); //tong thoi gian lam
        long totalAllowedSeconds = onluyen.getDeThi().getThoiGianLam() * 60; //thoi gian cua de
        long remainingSeconds = totalAllowedSeconds - elapsedSeconds; // thoi gian cua de tru thoi gian lam => thoi gian du
        if(remainingSeconds +10 > 0 ){
            //dung han (cho them 10s do delay)
            onluyen.setThoiGianKetThuc(now);
            onLuyenRepository.save(onluyen);

        }
        else{
            return "2";
        }
        String res = kqOnLuyenService.saveAllKq(req);
        return res;
    }
    public  List<CauHoiDTO_Res> checkNotLogin(List<KQOnLuyenDTO_Req> req){
        List<CauHoiDTO_Res> res = kqOnLuyenService.checkKqNotLogin(req);
        return res;
    }

}
