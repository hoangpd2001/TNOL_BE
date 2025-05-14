package com.learn.start.service;

import com.learn.start.dto.request.MonDTO_Req;
import com.learn.start.dto.response.MonDTO_Res;
import com.learn.start.entity.Lop;
import com.learn.start.entity.Mon;
import com.learn.start.repository.MonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonService {
    @Autowired
    private MonRepository monRepository;
    public List<MonDTO_Res> getAllMon(){
        List<Mon> list =  monRepository.findAll();
        List<MonDTO_Res> res = new ArrayList<MonDTO_Res>();
        for (Mon mon : list) {
            MonDTO_Res monDTO_Res = new MonDTO_Res();
            monDTO_Res.setId(mon.getId());
            monDTO_Res.setTenMon(mon.getTenMon());
            monDTO_Res.setIdLop(mon.getLop().getID());
            res.add(monDTO_Res);
            }
        return res;
    }
    public List<MonDTO_Res> getMonByLop(int idLop){
        List<Mon> list =  monRepository.findByLop_Id(idLop);
        List<MonDTO_Res> res = new ArrayList<MonDTO_Res>();
        for (Mon mon : list) {
            MonDTO_Res monDTO_Res = new MonDTO_Res();
            monDTO_Res.setId(mon.getId());
            monDTO_Res.setTenMon(mon.getTenMon());
            monDTO_Res.setIdLop(mon.getLop().getID());
            res.add(monDTO_Res);
        }
        return res;
    }
    public Optional<MonDTO_Res> getMonById(int id) {
        Optional<Mon> mon = monRepository.findById(id);
        if(mon.isPresent()) {
            MonDTO_Res monDTO_Res = new MonDTO_Res();
            monDTO_Res.setId(mon.get().getId());
            monDTO_Res.setTenMon(mon.get().getTenMon());
            monDTO_Res.setIdLop(mon.get().getLop().getID());
            return Optional.of(monDTO_Res);
        }
        return Optional.empty();
    }
    public MonDTO_Res addMon(MonDTO_Req monDTO_Req) {
        Mon mon = new Mon();
        mon.setTenMon(monDTO_Req.getTenMon());
        mon.setLop(new Lop(monDTO_Req.getIdLop()));
        monRepository.save(mon);
        MonDTO_Res monDTO_Res = new MonDTO_Res();
        monDTO_Res.setId(mon.getId());
        monDTO_Res.setTenMon(mon.getTenMon());
        return monDTO_Res;
    }
    public MonDTO_Res updateMon(MonDTO_Req monDTO_Req) {
        Mon mon = new Mon();
        mon.setId(monDTO_Req.getId());
        mon.setTenMon(monDTO_Req.getTenMon());
        mon.setLop(new Lop(monDTO_Req.getIdLop()));
        monRepository.save(mon);
        MonDTO_Res monDTO_Res = new MonDTO_Res();
        monDTO_Res.setId(mon.getId());
        monDTO_Res.setTenMon(mon.getTenMon());
        monDTO_Res.setIdLop(monDTO_Req.getIdLop());
        return monDTO_Res;
    }
    public Optional<MonDTO_Res> deleteMon(int id) {
      Optional<Mon> mon = monRepository.findById(id);
      if(mon.isPresent()) {
          monRepository.delete(mon.get());
          MonDTO_Res monDTO_Res = new MonDTO_Res();
          monDTO_Res.setId(mon.get().getId());
          monDTO_Res.setTenMon(mon.get().getTenMon());
          monDTO_Res.setIdLop(mon.get().getLop().getID());
          return Optional.of(monDTO_Res);
      }
      return Optional.empty();
    }
}
