package com.learn.start.service;

import com.learn.start.dto.request.CauHoiDTO_Req;
import com.learn.start.dto.request.KQOnLuyenDTO_Req;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.dto.response.DeThiDTO_Res;
import com.learn.start.dto.response.KQOnLuyenDTO_Res;
import com.learn.start.dto.response.OnLuyenDTO_Res;
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
import java.util.stream.Collectors;

@Service
public class OnLuyenService {
    @Autowired
    OnLuyenRepository onLuyenRepository;
    @Autowired
    KQOnLuyenService kqOnLuyenService;
    @Autowired
    CauHoiDeThiService cauHoiDeThiService;
    public OnLuyen findOne(Integer id) {
        return onLuyenRepository.findById(id).orElse(null);
    }
//kiem tra de thi da duoc tao chua, va tra ve nhung dap an da duoc chon
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
    public List<CauHoiDTO_Res> save(List<KQOnLuyenDTO_Req> req){
        Integer id = req.getFirst().getIdOnLuyen();
        OnLuyen onluyen = onLuyenRepository.findById(id).orElse(null);
        if(onluyen == null || onluyen.getThoiGianKetThuc() != null){ return null;}
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
            System.out.println("het gio roi");
            return null;
        }
        return  kqOnLuyenService.saveAllKq(req);

    }
    public List<CauHoiDTO_Res> getKQOnLuyen(Integer OnLuyenID){

        OnLuyen onluyen = onLuyenRepository.findById(OnLuyenID).orElse(null);

        return  kqOnLuyenService.getKqtoOnLuyen(onluyen);

    }
    public  List<CauHoiDTO_Res> checkNotLogin(List<KQOnLuyenDTO_Req> req){
        List<CauHoiDTO_Res> res = kqOnLuyenService.checkKqNotLogin(req);
        return res;
    }
    public List<OnLuyenDTO_Res> getbyUsers(Integer lop, Integer mon, Integer chuong, Integer idUser) {
        if(idUser == null){
            if(chuong != null){
                return onLuyenRepository.findByDeThi_Chuong_Id(chuong).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            if(mon != null){
                return onLuyenRepository.findByDeThi_Chuong_Mon_Id(mon).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            if(lop != null){
                return onLuyenRepository.findByDeThi_Chuong_Mon_Lop_Id(lop).stream().map(this::convertToDTO).collect(Collectors.toList());
            }

        }else{
            if(chuong != null){
                return onLuyenRepository.findByUsers_IdAndDeThi_Chuong_Id(idUser, chuong).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            if(mon != null){
                return onLuyenRepository.findByUsers_IdAndDeThi_Chuong_Mon_Id(idUser,mon).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            if(lop != null){
                return onLuyenRepository.findByUsers_IdAndDeThi_Chuong_Mon_Lop_Id( idUser,lop).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            return onLuyenRepository.findByUsers_Id(idUser).stream().map(this::convertToDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private OnLuyenDTO_Res convertToDTO(OnLuyen onLuyen) {
        List<CauHoiDTO_Res> cauhois = cauHoiDeThiService.getByIdDeThi(onLuyen.getDeThi().getId());
        int l = cauhois.size();
        List<KQOnLuyenDTO_Res> kqs = kqOnLuyenService.getKQ(onLuyen.getId());
        int dem = 0;
        if(kqs != null){
            for(KQOnLuyenDTO_Res kq : kqs){
                for(CauHoiDTO_Res cauHoi : cauhois){
                    if(kq.getIdCauHoi() == cauHoi.getId()){
                        if(kq.getDapAn().toUpperCase().equals(cauHoi.getDapAn().toUpperCase())){
                            dem++;
                        }
                    }
                }
            }
        }
        return new OnLuyenDTO_Res(
                onLuyen.getId(),
                onLuyen.getDeThi().getId(),
                onLuyen.getUsers().getId(),
                onLuyen.getThoiGianBatDau(),
                onLuyen.getThoiGianKetThuc(),
                onLuyen.getTrangThai(),
                l,
                dem,
                onLuyen.getDeThi().getTenDeThi(),
                onLuyen.getDeThi().getChuong().getTenChuong(),
                onLuyen.getDeThi().getChuong().getMon().getTenMon(),
                onLuyen.getDeThi().getChuong().getMon().getLop().getTenLop(),
                onLuyen.getDeThi().getGiaoVien().getHoTen()
        );

    }
}
