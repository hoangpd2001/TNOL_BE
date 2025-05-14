package com.learn.start.service;

import com.learn.start.dto.request.DeThiDTO_Req;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.dto.response.DeThiDTO_Res;
import com.learn.start.entity.CauHoiDeThi;
import com.learn.start.entity.Chuong;
import com.learn.start.entity.DeThi;
import com.learn.start.entity.GiaoVien;
import com.learn.start.repository.CauHoiRepository;
import com.learn.start.repository.ChuongRepository;
import com.learn.start.repository.DeThiRepository;
import com.learn.start.repository.GiaoVienRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeThiService {

    @Autowired
    private DeThiRepository deThiRepository;

    @Autowired
    private GiaoVienRepository giaoVienRepository;

    @Autowired
    private ChuongRepository chuongRepository;
    @Autowired
    private CauHoiRepository cauHoiRepository;
    @Autowired
    private CauHoiDeThiService cauHoiDeThiService;


    public List<DeThiDTO_Res> getAll() {
        return deThiRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<DeThiDTO_Res> getAllbyGiaoVien(Integer idgv) {
        return deThiRepository.findByGiaoVien_Id(idgv).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<DeThiDTO_Res> getbyGiaoVien(Integer lop, Integer mon, Integer chuong,Integer idgv) {
        if(idgv == null){
            if(chuong != null){
                return deThiRepository.findByChuong_Id(chuong).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            if(mon != null){
                return deThiRepository.findByChuong_Mon_Id(mon).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            if(lop != null){
                return deThiRepository.findByChuong_Mon_Lop_Id(lop).stream().map(this::convertToDTO).collect(Collectors.toList());
            }

        }else{
            if(chuong != null){
                return deThiRepository.findByGiaoVien_IdAndChuong_Id(idgv, chuong).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            if(mon != null){
                return deThiRepository.findByGiaoVien_IdAndChuong_Mon_Id(idgv,mon).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            if(lop != null){
                System.out.println("lop:"+lop);
                return deThiRepository.findByGiaoVien_IdAndChuong_Mon_Lop_Id( idgv,lop).stream().map(this::convertToDTO).collect(Collectors.toList());
            }
            return deThiRepository.findByGiaoVien_Id(idgv).stream().map(this::convertToDTO).collect(Collectors.toList());
        }
         return new ArrayList<>();
    }

    public DeThiDTO_Res getById(Integer id) {
        Optional<DeThi> deThi = deThiRepository.findById(id);
        return convertToDTO(deThi.get());
    }


    public DeThiDTO_Res create(DeThiDTO_Req dto) {
        DeThi deThi = new DeThi();
        deThi.setThoiGianLam(dto.getThoiGianLam());
        deThi.setTrangThai(dto.getTrangThai());
        deThi.setGiaTien(dto.getGiaTien());
        GiaoVien gv = giaoVienRepository.findById(dto.getIdGiaoVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên"));
        Chuong chuong = chuongRepository.findById(dto.getIdChuong())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chương"));

        deThi.setGiaoVien(gv);
        deThi.setChuong(chuong);

        return convertToDTO(deThiRepository.save(deThi));
    }


    public DeThiDTO_Res update(Integer id, DeThiDTO_Req dto) {
        DeThi deThi = deThiRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy đề thi"));
        deThi.setThoiGianLam(dto.getThoiGianLam());
        deThi.setTrangThai(dto.getTrangThai());
        deThi.setGiaTien(dto.getGiaTien());

        GiaoVien gv = giaoVienRepository.findById(dto.getIdGiaoVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên"));
        Chuong chuong = chuongRepository.findById(dto.getIdChuong())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chương"));

        deThi.setGiaoVien(gv);
        deThi.setChuong(chuong);

        return convertToDTO(deThiRepository.save(deThi));
    }


    public void delete(Integer id) {
        if (!deThiRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy đề thi");
        }
        deThiRepository.deleteById(id);
    }

    private DeThiDTO_Res convertToDTO(DeThi deThi) {
         int l = cauHoiDeThiService.getByIdDeThi(deThi.getId()).size();
        return new DeThiDTO_Res(
                deThi.getId(),
                deThi.getTenDeThi(),
                deThi.getGiaoVien().getHoTen(),
                deThi.getChuong().getTenChuong(),
                deThi.getThoiGianLam(),
                deThi.getTrangThai(),
                deThi.getGiaTien(),
                deThi.getNgayTao(),
                deThi.getLuotXem(),
                deThi.getGiaoVien().getAnh(),
                deThi.getGiaoVien().getTruong(),
                l,
                deThi.getGiaoVien().getId(),
                deThi.getChuong().getMon().getLop().getTenLop(),
                deThi.getChuong().getMon().getTenMon()
        );
    }
}
