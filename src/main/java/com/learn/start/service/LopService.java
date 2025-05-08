package com.learn.start.service;

import com.learn.start.dto.request.LopDTO_Req;
import com.learn.start.dto.response.LopDTO_Res;
import com.learn.start.entity.Lop;
import com.learn.start.repository.LopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LopService {

    @Autowired
    private LopRepository lopRepository;

    public List<LopDTO_Res>  getAllLop(){
        List<Lop> list = lopRepository.findAll();
        List<LopDTO_Res> res = new ArrayList<>();
        for(Lop lop : list) {
            LopDTO_Res lopDTO_Res = new LopDTO_Res();
            lopDTO_Res.setId(lop.getID());
            lopDTO_Res.setTenLop(lop.getTenLop());
            res.add(lopDTO_Res);
            }
        return res;
    }
    public Optional<LopDTO_Res> getLopById(int id) {
        Optional<Lop> lop = lopRepository.findById(id);
        if (lop.isPresent()) {
            LopDTO_Res lopDTO_Res = new LopDTO_Res();
            Lop lopEntity = lop.get();
            lopDTO_Res.setId(lopEntity.getID());
            lopDTO_Res.setTenLop(lopEntity.getTenLop());
            return Optional.of(lopDTO_Res);
        }
        return Optional.empty();
    }

    public LopDTO_Res save(LopDTO_Req lop){
        Lop lopEntity = new Lop();
        lopEntity.setTenLop(lop.getTenLop());
        lopEntity = lopRepository.save(lopEntity);
        LopDTO_Res res = new LopDTO_Res();
        res.setTenLop(lopEntity.getTenLop());
        res.setId(lopEntity.getID());
        return res;
    }
    public LopDTO_Res update(LopDTO_Req lop){
        Lop lopEntity = new Lop();
        lopEntity.setID(lop.getId());
        lopEntity.setTenLop(lop.getTenLop());
        lopEntity = lopRepository.save(lopEntity);
        LopDTO_Res res = new LopDTO_Res();
        res.setTenLop(lopEntity.getTenLop());
        res.setId(lopEntity.getID());
        return res;
    }
    public Optional<LopDTO_Res> delete(int id){
        Optional<Lop> lop = lopRepository.findById(id);
        if (lop.isPresent()) {
            Lop lopEntity = lop.get();
            lopRepository.delete(lopEntity);
            LopDTO_Res res = new LopDTO_Res();
            res.setId(lopEntity.getID());
            res.setTenLop(lopEntity.getTenLop());
            return Optional.of(res);
        }
        return Optional.empty();
    }
}
