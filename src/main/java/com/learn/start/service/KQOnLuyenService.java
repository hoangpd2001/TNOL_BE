package com.learn.start.service;

import com.learn.start.dto.request.KQOnLuyenDTO_Req;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.dto.response.KQOnLuyenDTO_Res;
import com.learn.start.entity.CauHoi;
import com.learn.start.entity.KQOnLuyen;
import com.learn.start.entity.KQOnLuyenID;
import com.learn.start.entity.OnLuyen;
import com.learn.start.repository.KQOnLuyenRepository;
import com.learn.start.repository.OnLuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KQOnLuyenService {
    @Autowired
    KQOnLuyenRepository kqOnLuyenRepository;
@Autowired
CauHoiDeThiService cauHoiDeThiService;
    public KQOnLuyenDTO_Res addKQ(KQOnLuyenDTO_Req req){
        KQOnLuyen kq = new KQOnLuyen();
        kq.setId(new KQOnLuyenID(req.getIdCauHoi(), req.getIdOnLuyen()));
        kq.setDapan(req.getDapAn());
        kq.setOnLuyen(new OnLuyen(req.getIdOnLuyen()));
        kq.setCauHoi(new CauHoi(req.getIdCauHoi()));
        kqOnLuyenRepository.save(kq);
        return null;
    }
    public List<KQOnLuyenDTO_Res> getKQ(Integer OnLuyenID){

         List<KQOnLuyen> list =  kqOnLuyenRepository.findKQOnLuyenByOnLuyenId(OnLuyenID);
        List<KQOnLuyenDTO_Res> res = new ArrayList<KQOnLuyenDTO_Res>();
         for(KQOnLuyen onLuyen : list){
             KQOnLuyenDTO_Res r = new KQOnLuyenDTO_Res(onLuyen.getCauHoi().getId(), onLuyen.getOnLuyen().getId(),onLuyen.getDapan());
             res.add(r);
        }
        return res;
    }

    public String saveAllKq(List<KQOnLuyenDTO_Req> listreq){
        List<KQOnLuyen> list = new ArrayList<>();
        for(KQOnLuyenDTO_Req req : listreq){
            KQOnLuyen kq = new KQOnLuyen();
            kq.setId(new KQOnLuyenID(req.getIdCauHoi(), req.getIdOnLuyen()));
            kq.setDapan(req.getDapAn());
            kq.setOnLuyen(new OnLuyen(req.getIdOnLuyen()));
            kq.setCauHoi(new CauHoi(req.getIdCauHoi()));
            list.add(kq);
        }
        list = kqOnLuyenRepository.saveAll(list);
        int dem = cauHoiDeThiService.getByIdDeThi(list.getFirst().getOnLuyen().getDeThi().getId()).size();
        int dung = 0;
        for(KQOnLuyen kqonLuyen : list){
            if(kqonLuyen.getDapan().toUpperCase().equals(kqonLuyen.getCauHoi().getDapAn().toUpperCase())){
                ++dung;
            }
//            CauHoiDTO_Res res = new CauHoiDTO_Res();
//            res.setDe(kqonLuyen.getCauHoi().getDe());
//            res.setA(kqonLuyen.getCauHoi().getA());
//            res.setB(kqonLuyen.getCauHoi().getB());
//            res.setC(kqonLuyen.getCauHoi().getC());
//            res.setD(kqonLuyen.getCauHoi().getD());
//            res.setDapAn(kqonLuyen.getCauHoi().getDapAn());
//            res.setDaOnLuyen(kqonLuyen.getDapan());
//            res.setHinhAnh(kqonLuyen.getCauHoi().getHinhAnh());
//            res.setHinhAnhDa(kqonLuyen.getCauHoi().getHinhAnhDa());
//            res.setId(kqonLuyen.getCauHoi().getId());
//            res.setMucDo(kqonLuyen.getCauHoi().getMucDo());
        }
        String x = dung + "/" + dem;
                ;
        return x;
    }
    public List<CauHoiDTO_Res> checkKqNotLogin(List<KQOnLuyenDTO_Req> listreq) {

        Integer idDeThi= listreq.getFirst().getIdOnLuyen();
        List<CauHoiDTO_Res> listCauHoi = cauHoiDeThiService.getByIdDeThi(idDeThi);
        Map<Integer, String> answersMap = listreq.stream()
                .collect(Collectors.toMap(KQOnLuyenDTO_Req::getIdCauHoi, KQOnLuyenDTO_Req::getDapAn));
        listCauHoi.forEach(cauhoi -> {
            String answer = answersMap.get(cauhoi.getId());
            if (answer != null) {
                cauhoi.setDaOnLuyen(answer);
            }
        });
//        for(CauHoiDTO_Res c : listCauHoi){
//            System.out.println(c.getDapAn());
//            System.out.println(c.getDaOnLuyen());
//        }
        return listCauHoi;
    }

}

