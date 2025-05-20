package com.learn.start.service;

import com.learn.start.dto.response.DeThiDTO_Res;
import com.learn.start.dto.response.UsersDTO_Res;
import com.learn.start.entity.*;
import com.learn.start.repository.MuaDeThiRepository;
import com.learn.start.repository.UsersRepository;
import com.learn.start.repository.DeThiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MuaDeThiService {
    @Autowired
    MuaDeThiRepository muaDeThiRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    DeThiRepository deThiRepository;
    @Autowired
    private UserService userService;

    public boolean checkMuaDeThi(Integer idUsers, Integer idDeThi) {
        MuaDeThiID id = new MuaDeThiID(idUsers, idDeThi);
        boolean x = muaDeThiRepository.existsById(id);
        System.out.println( x);
        return x;
    }

    public MuaDeThi creatMuaDeThi(Integer idUsers, Integer idDeThi) {
        MuaDeThiID id = new MuaDeThiID(idUsers, idDeThi);
        if (muaDeThiRepository.existsById(id)) return null;

        Optional<Users> users = usersRepository.findById(idUsers);
        Optional<DeThi> deThi = deThiRepository.findById(idDeThi);
        if (!users.isPresent() || !deThi.isPresent()) return null;
        else {
            if(users.get().getSoDu() < deThi.get().getGiaTien()){
                return null;
            }
        }
        if(!userService.updateSoDu(idUsers, 0-deThi.get().getGiaTien())){
            return null;
        }
        MuaDeThi muaDeThi = new MuaDeThi();
        muaDeThi.setId(id);
        muaDeThi.setUsers(users.get());
        muaDeThi.setDeThi(deThi.get());
        muaDeThi.setCreateDate(new Timestamp(System.currentTimeMillis()));
        muaDeThi.setSoTien(deThi.get().getGiaTien().longValue());

        return muaDeThiRepository.save(muaDeThi);
    }

    public List<UsersDTO_Res> getMuaDeThibyDeThi(Integer idDeThi) {
        List<MuaDeThi> list = muaDeThiRepository.findByDeThiId(idDeThi);
        return list.stream().map(mua -> {
            Users u = mua.getUsers();
            return new UsersDTO_Res(u.getId(), u.getHoTen(), u.getGmail());
        }).collect(Collectors.toList());
    }

    public List<DeThiDTO_Res> getMuaDeThiByUser(Integer idUser) {
        List<MuaDeThi> list = muaDeThiRepository.findByUsersId(idUser);
        return list.stream().map(mua -> {
            DeThi d = mua.getDeThi();
            return new DeThiDTO_Res(d.getId(), d.getTenDeThi(), d.getGiaTien());
        }).collect(Collectors.toList());
    }
}