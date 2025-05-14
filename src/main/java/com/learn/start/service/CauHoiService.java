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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        List<CauHoi> list = cauHoiRepository.getAllWithRelations();
        List<CauHoiDTO_Res> res = new ArrayList<>();

        for (CauHoi c : list) {
            CauHoiDTO_Res dto = new CauHoiDTO_Res();
            dto.setId(c.getId());
            dto.setTenChuong(c.getChuong() != null ? c.getChuong().getTenChuong() : null);
            dto.setTenGiaoVien(c.getGiaoVien() != null ? c.getGiaoVien().getHoTen() : null);
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
    public List<CauHoiDTO_Res> filterCauHoi(Integer idLop, Integer idMon, Integer idChuong, Integer mucDo) {
        List<CauHoi> list;


        if (idChuong != null) {
            if (mucDo != null) {
                list = cauHoiRepository.findByChuong_IdAndMucDo(idChuong, mucDo);
            } else {
                list = cauHoiRepository.findByChuong_Id( idChuong);
            }
        } else if (idMon != null) {
            if (mucDo != null) {
                list = cauHoiRepository.findByChuong_Mon_IdAndMucDo( idMon, mucDo);
            } else {
                list = cauHoiRepository.findByChuong_Mon_Id(idMon);
            }
        } else if (idLop != null) {
            if (mucDo != null) {
                list = cauHoiRepository.findByChuong_Mon_Lop_IdAndMucDo(idLop, mucDo);
            } else {
                list = cauHoiRepository.findByChuong_Mon_Lop_Id(idLop);
            }
        } else {
            // Không có gì -> trả tất cả
            if (mucDo != null) {
                list = cauHoiRepository.findByMucDo(mucDo);
            } else {
                list = cauHoiRepository.findAll();
            }
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
    public List<CauHoiDTO_Res> filterCauHoiGV(Integer idLop, Integer idMon, Integer idChuong, Integer mucDo, Integer idGiaoVien) {
        List<CauHoi> list;

        if ( idChuong != null) {
            if (mucDo != null) {
                list = cauHoiRepository.findByChuong_IdAndMucDoAndGiaoVien_Id(idChuong, mucDo,idGiaoVien);
            } else {
                list = cauHoiRepository.findByChuong_IdAndGiaoVien_Id( idChuong,idGiaoVien);
            }
        } else if ( idMon != null) {
            if (mucDo != null) {
                list = cauHoiRepository.findByChuong_Mon_IdAndMucDoAndGiaoVien_Id( idMon, mucDo,idGiaoVien);
            } else {
                list = cauHoiRepository.findByChuong_Mon_IdAndGiaoVien_Id(idMon,idGiaoVien);
            }
        } else if (idLop != null) {
            if (mucDo != null) {
                list = cauHoiRepository.findByChuong_Mon_Lop_IdAndMucDoAndGiaoVien_Id(idLop, mucDo,idGiaoVien);
            } else {
                list = cauHoiRepository.findByChuong_Mon_Lop_IdAndGiaoVien_Id(idLop,idGiaoVien);
            }
        } else {
            // Không có gì -> trả tất cả
            if (mucDo != null) {
                list = cauHoiRepository.findByMucDoAndGiaoVien_Id(mucDo,idGiaoVien);
            } else {
                list = cauHoiRepository.findAll();
            }
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
        c.setHinhAnh(null);
        c.setTrangThai(req.getTrangThai());
        c.setChiTiet(req.getChiTiet());
        c.setHinhAnhDa(null);
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
            ch.setGiaoVien(new GiaoVien(dto.getIdGiaoVien()));
            ch.setTrangThai(1);
            ch.setHinhAnh(null);
            ch.setHinhAnhDa(null);
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

/// import danh sach cau hoi co hinh anh
    public Integer importQuestionsImageImpInt(List<CauHoiDTO_Req> dtoList) {
       return importQuestionsImage(dtoList).size();
    }
    public List<CauHoi> importQuestionsImageImpList(List<CauHoiDTO_Req> dtoList) {
        return importQuestionsImage(dtoList);
    }
    public List<CauHoi> importQuestionsImage(List<CauHoiDTO_Req> dtoList) { List<CauHoi> entities = new ArrayList<>();

        for (CauHoiDTO_Req dto : dtoList) {
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
            ch.setGiaoVien(new GiaoVien(dto.getIdGiaoVien()));
            ch.setTrangThai(1);
            ch.setHinhAnh(null);
            ch.setHinhAnhDa(null);
            entities.add(ch);
        }

        // Lưu tạm để lấy ID
        List<CauHoi> saved = cauHoiRepository.saveAll(entities);



        for (int i = 0; i < saved.size(); i++) {
            CauHoi ch = saved.get(i);
            CauHoiDTO_Req dto = dtoList.get(i);
            try {
                if (dto.getHinhAnh() != null && !dto.getHinhAnh().isEmpty()) {
                    String fileName = dto.getHinhAnh().getOriginalFilename();
                    String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    String imgNewName = ch.getId() + "." + ext;
                    Path uploadPath = Paths.get("uploads", "question/question");
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    Files.copy(dto.getHinhAnh().getInputStream(), uploadPath.resolve(imgNewName), StandardCopyOption.REPLACE_EXISTING);
                    ch.setHinhAnh(imgNewName);
                }

                if (dto.getHinhAnhDa() != null && !dto.getHinhAnhDa().isEmpty()) {
                    String fileName = dto.getHinhAnhDa().getOriginalFilename();
                    String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    String imgNewName = ch.getId() + "." + ext;
                    Path uploadPath = Paths.get("uploads", "question/answer");
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    Files.copy(dto.getHinhAnhDa().getInputStream(), uploadPath.resolve(imgNewName), StandardCopyOption.REPLACE_EXISTING);
                    ch.setHinhAnhDa(imgNewName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        saved = cauHoiRepository.saveAll(saved);
//        List<CauHoiDTO_Res> res = new ArrayList<>();
//        for (CauHoi c : saved) {
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
//            dto.setHinhAnhDa(c.getHinhAnhDa());
//            dto.setTrangThai(c.getTrangThai());
//            dto.setChiTiet(c.getChiTiet());
//            res.add(dto);
//        }
        return saved;
    }
}
