package com.learn.start.service;


import com.learn.start.dto.request.UsersDTO_Req;
import com.learn.start.dto.response.UsersDTO_Res;
import com.learn.start.entity.Users;
import com.learn.start.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;

    public Optional<UsersDTO_Res> getUserByGmail(String gmail) {
        return userRepository.findByGmail(gmail)
                .map(user -> {
                    UsersDTO_Res dto = new UsersDTO_Res();
                    dto.setId(user.getId());
                    dto.setGmail(user.getGmail());
                    dto.setMk(user.getMk());
                    dto.setRoleName(user.getRole().getTenRole());
                    dto.setRoleId(user.getRole().getId());
                    return dto;
                });
    }


    public Optional<UsersDTO_Res> getUserById(int id) {
        return userRepository.getUsersById(id).
                map(user -> {
            UsersDTO_Res dto = new UsersDTO_Res();
            dto.setId(user.getId());
            dto.setGmail(user.getGmail());
            dto.setMk(user.getMk());
            dto.setRoleName(user.getRole().getTenRole());
            dto.setRoleId(user.getRole().getId());
            dto.setAnh(user.getAnh());
            dto.setNgaySinh(user.getNgaySinh());
            dto.setTruong(user.getTruong());
            dto.setSdt(user.getSdt());
            return dto;
        });

    }

//
//    public UsersDTO_Res createUser(UsersDTO_Req req) {
//        Users user = new Users();
//        user.setHoTen(req.getHoTen());
//        user.setGmail(req.getGmail());
//        user.setSdt(req.getSdt());
//        user.setTrangThai(1);  // Mặc định trạng thái active
//
//        user = userRepository.save(user);
//        return mapToDTO(user);
//    }
//
//    public UsersDTO_Res updateUser(UsersDTO_Req req) {
//        Optional<Users> userOptional = userRepository.findById(req.getId());
//        if (!userOptional.isPresent()) {
//            throw new RuntimeException("Không tìm thấy người dùng với ID: " + req.getId());
//        }
//
//        Users user = userOptional.get();
//        if (req.getHoTen() != null) user.setHoTen(req.getHoTen());
//        if (req.getGmail() != null) user.setGmail(req.getGmail());
//        if (req.getSdt() != null) user.setSdt(req.getSdt());
//        if (req.getTrangThai() != null) user.setTrangThai(req.getTrangThai());
//
//        user = userRepository.save(user);
//        return mapToDTO(user);
//    }
//
//    private UsersDTO_Res mapToDTO(Users user) {
//        UsersDTO_Res res = new UsersDTO_Res();
//        res.setId(user.getId());
//        res.setHoTen(user.getHoTen());
//        res.setGmail(user.getGmail());
//        res.setSdt(user.getSdt());
//        res.setTrangThai(user.getTrangThai());
//        return res;
//    }
}
