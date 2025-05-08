package com.learn.start.service;


import com.learn.start.dto.request.CauHoiDTO_Req;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.entity.CauHoi;
import com.learn.start.entity.Chuong;
import com.learn.start.entity.GiaoVien;
import com.learn.start.repository.CauHoiRepository;
import com.learn.start.repository.ChuongRepository;
import com.learn.start.repository.GiaoVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CauHoiService {

    @Autowired
    private CauHoiRepository cauHoiRepository;

    @Autowired
    private ChuongRepository chuongRepository;

    @Autowired
    private GiaoVienRepository giaoVienRepository;

    public List<CauHoiDTO_Res> getAllCauHoi() {
//        List<CauHoi> list = cauHoiRepository.getAllWithRelations();
//        List<CauHoiDTO_Res> res = new ArrayList<>();
//
//        for (CauHoi c : list) {
//            CauHoiDTO_Res dto = new CauHoiDTO_Res();
//            dto.setId(c.getId());
//            dto.setTenChuong(c.getChuong() != null ? c.getChuong().getTenChuong() : null);
//            dto.setTenGiaoVien(c.getGiaoVien() != null ? c.getGiaoVien().getHoTen() : null);
//            dto.setMucDo(c.getMucDo());
//            dto.setDe(c.getDe());
//            dto.setA(c.getA());
//            dto.setB(c.getB());
//            dto.setC(c.getC());
//            dto.setD(c.getD());
//            dto.setDapAn(c.getDapAn());
//            dto.setHinhAnh(c.getHinhAnh());
//            dto.setTrangThai(c.getTrangThai());
//            dto.setChiTiet(c.getChiTiet());
//            dto.setHinhAnhDa(c.getHinhAnhDa());
//            res.add(dto);
//        }
//
//        return res;
        return null;
    }
    public List<CauHoiDTO_Res> filterCauHoi(int idGiaoVien, int idChuong, Integer mucDo) {
        List<CauHoi> list;

        if (mucDo != null ) {
            list = cauHoiRepository.findByGiaoVienIdAndChuongIdAndMucDo(idGiaoVien, idChuong, mucDo);
        } else {
            list = cauHoiRepository.findByGiaoVienIdAndChuongId(idGiaoVien, idChuong);
        }

        List<CauHoiDTO_Res> res = new ArrayList<>();
        for (CauHoi c : list) {

            CauHoiDTO_Res dto = new CauHoiDTO_Res();
            dto.setId(c.getId());
            dto.setTenChuong(c.getChuong() != null ? c.getChuong().getTenChuong() : null);
          //  dto.setTenGiaoVien(c.getGiaoVien() != null ? c.getGiaoVien().getHoTen() : null);
            dto.setMucDo(c.getMucDo());
            dto.setDe(c.getDe());
            dto.setA(c.getA());
            dto.setB(c.getB());
            dto.setC(c.getC());
            dto.setD(c.getD());
            dto.setDapAn(c.getDapAn());
            dto.setHinhAnh(c.getHinhAnh());
            dto.setTrangThai(c.getTrangThai());
            dto.setChiTiet(c.getChiTiet());
            dto.setHinhAnhDa(c.getHinhAnhDa());
            res.add(dto);
        }

        return res;
    }

    public CauHoi createCauHoi(CauHoiDTO_Req req) {
        CauHoi c = new CauHoi();
        c.setChuong(chuongRepository.findById(req.getIdChuong()).orElse(null));
        c.setGiaoVien(giaoVienRepository.findById(req.getIdGiaoVien()).orElse(null));
        c.setMucDo(req.getMucDo());
        c.setDe(req.getDe());
        c.setA(req.getA());
        c.setB(req.getB());
        c.setC(req.getC());
        c.setD(req.getD());
        c.setDapAn(req.getDapAn());
        c.setHinhAnh(req.getHinhAnh());
        c.setTrangThai(req.getTrangThai());
        c.setChiTiet(req.getChiTiet());
        c.setHinhAnhDa(req.getHinhAnhDa());
        return cauHoiRepository.save(c);
    }

    public List<CauHoiDTO_Res> importQuestions(List<CauHoiDTO_Req> dtoList) {
        List<CauHoi> entities = dtoList.stream().map(dto -> {
            CauHoi ch = new CauHoi();
            ch.setDe(dto.getDe());
            ch.setA(dto.getA());
            ch.setB(dto.getB());
            ch.setC(dto.getC());
            ch.setD(dto.getD());
            ch.setDapAn(dto.getDapAn());
            ch.setChiTiet(dto.getChiTiet());
            ch.setMucDo(dto.getMucDo());
            ch.setChuong(new Chuong(dto.getIdChuong()));
           // ch.setGiaoVien(new GiaoVien(dto.getIdGiaoVien()));
            ch.setTrangThai(dto.getTrangThai());
            ch.setHinhAnh(dto.getHinhAnh());
            ch.setHinhAnhDa(dto.getHinhAnhDa());
            return ch;
        }).toList();
        List<CauHoiDTO_Res> res = new ArrayList<>();
        List<CauHoi> list = cauHoiRepository.saveAll(entities);
        for (CauHoi c : list) {
            CauHoiDTO_Res dto = new CauHoiDTO_Res();
            dto.setId(c.getId());
            dto.setTenChuong(c.getChuong() != null ? c.getChuong().getTenChuong() : null);
          //  dto.setTenGiaoVien(c.getGiaoVien() != null ? c.getGiaoVien().getHoTen() : null);
            dto.setMucDo(c.getMucDo());
            dto.setDe(c.getDe());
            dto.setA(c.getA());
            dto.setB(c.getB());
            dto.setC(c.getC());
            dto.setD(c.getD());
            dto.setDapAn(c.getDapAn());
            dto.setHinhAnh(c.getHinhAnh());
            dto.setTrangThai(c.getTrangThai());
            dto.setChiTiet(c.getChiTiet());
            dto.setHinhAnhDa(c.getHinhAnhDa());
            res.add(dto);
        }
        return res;
    }
}
