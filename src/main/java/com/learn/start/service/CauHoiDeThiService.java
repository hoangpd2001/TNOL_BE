package com.learn.start.service;

import com.learn.start.dto.request.CauHoiDTO_Req;
import com.learn.start.dto.request.CauHoiDeThiDTO_Req;
import com.learn.start.dto.request.DeThiDTO_Req;
import com.learn.start.dto.request.DeThiRequest;
import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.dto.response.CauHoiDeThiDTO_Res;
import com.learn.start.entity.*;
import com.learn.start.repository.CauHoiDeThiRepository;
import com.learn.start.repository.CauHoiRepository;
import com.learn.start.repository.DeThiRepository;
import com.learn.start.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CauHoiDeThiService {

    @Autowired
    private CauHoiDeThiRepository cauHoiDeThiRepository;

    @Autowired
    private CauHoiRepository cauHoiRepository;

    @Autowired
    private DeThiRepository deThiRepository;
    @Autowired
    private CauHoiService cauHoiService;
    public Optional<CauHoiDeThiDTO_Res> getById(int idCauHoi, int idDeThi) {
        CauHoiDeThiId id = new CauHoiDeThiId(idCauHoi, idDeThi);
        return cauHoiDeThiRepository.findById(id).map(this::mapToDTO);
    }
    public List<CauHoiDTO_Res> getByIdDeThi(int idDeThi) {
        //CauHoiDeThiId id = new CauHoiDeThiId(idCauHoi, idDeThi);
        List<CauHoiDeThi> list = cauHoiDeThiRepository.findByDeThiId(idDeThi);
        List<CauHoiDTO_Res> res = new ArrayList<>();
        if(!list.isEmpty()){
            for(CauHoiDeThi c : list){
                CauHoiDTO_Res resDTO = new CauHoiDTO_Res();
                resDTO.setDe(c.getCauHoi().getDe());
                resDTO.setId(c.getCauHoi().getId());
                resDTO.setA(c.getCauHoi().getA());
                resDTO.setB(c.getCauHoi().getB());
                resDTO.setC(c.getCauHoi().getC());
                resDTO.setD(c.getCauHoi().getD());
                resDTO.setTenGiaoVien(c.getDeThi().getGiaoVien().getHoTen());
                resDTO.setDapAn(c.getCauHoi().getDapAn());
                resDTO.setTrangThai(c.getCauHoi().getTrangThai());
                resDTO.setHinhAnh(c.getCauHoi().getHinhAnh());
                resDTO.setHinhAnhDa(c.getCauHoi().getHinhAnhDa());
                resDTO.setChiTiet(c.getCauHoi().getChiTiet());
                res.add(resDTO);
            }
            return res;
        }
        return res;
    }


    public int create(DeThiRequest req) {
        List<CauHoiDeThiDTO_Req> cauHoiDeThiDTOReqList= req.getCauHoiDeThis();
        DeThiDTO_Req deThiDTO_Req = req.getDeThi();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int id = userDetails.getId();
        DeThi deThi = new DeThi();
        deThi.setGiaoVien(new GiaoVien(id));
        deThi.setChuong(new Chuong(deThiDTO_Req.getIdChuong()));
        deThi.setGiaTien(deThiDTO_Req.getGiaTien());
        deThi.setTenDeThi(deThiDTO_Req.getTenDeThi());
        deThi.setTrangThai(1);
        deThi.setThoiGianLam(deThiDTO_Req.getThoiGianLam());
        deThi = deThiRepository.save(deThi);
        int dem = 0;
        for(CauHoiDeThiDTO_Req c : cauHoiDeThiDTOReqList){
            CauHoiDeThi cauHoiDeThi = new CauHoiDeThi();
            cauHoiDeThi.setId(new CauHoiDeThiId(c.getIdCauHoi(), deThi.getId()));
            cauHoiDeThi.setCauHoi(new CauHoi(c.getIdCauHoi()));
            cauHoiDeThi.setDeThi(deThi);
            cauHoiDeThi = cauHoiDeThiRepository.save(cauHoiDeThi);
            dem++;
        }
        return dem;
    }
    public int importDeThi(DeThiDTO_Req deThiReq, List<CauHoiDTO_Req> listCauHoiDTO_Req) {
        List<CauHoi> listCauHoi = cauHoiService.importQuestionsImageImpList(listCauHoiDTO_Req);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int id = userDetails.getId();
        int dem = 0;
        if (!listCauHoi.isEmpty()) {

            DeThi deThi = new DeThi();
            deThi.setGiaoVien(new GiaoVien(id));
            deThi.setChuong(new Chuong(deThiReq.getIdChuong()));
            deThi.setGiaTien(deThiReq.getGiaTien());
            deThi.setTenDeThi(deThiReq.getTenDeThi());
            deThi.setTrangThai(1);
            deThi.setThoiGianLam(deThiReq.getThoiGianLam());
            deThi = deThiRepository.save(deThi);
            dem = 0;
            for (CauHoi c : listCauHoi) {
                CauHoiDeThi cauHoiDeThi = new CauHoiDeThi();
                cauHoiDeThi.setId(new CauHoiDeThiId(c.getId(), deThi.getId()));
                cauHoiDeThi.setCauHoi(new CauHoi(c.getId()));
                cauHoiDeThi.setDeThi(deThi);
                cauHoiDeThi = cauHoiDeThiRepository.save(cauHoiDeThi);
                dem++;
            }

        }
        return dem;
    }
    public void delete(int idCauHoi, int idDeThi) {
        cauHoiDeThiRepository.deleteById(new CauHoiDeThiId(idCauHoi, idDeThi));

    }

    private CauHoiDeThiDTO_Res mapToDTO(CauHoiDeThi entity) {
        CauHoiDeThiDTO_Res dto = new CauHoiDeThiDTO_Res();
        dto.setIdCauHoi(entity.getCauHoi().getId());
    //    dto.setNoiDungCauHoi(entity.getCauHoi().getNoiDung());
        dto.setIdDeThi(entity.getDeThi().getId());
      //  dto.setTenDeThi(entity.getDeThi().getTenDeThi());
        return dto;
    }
}
