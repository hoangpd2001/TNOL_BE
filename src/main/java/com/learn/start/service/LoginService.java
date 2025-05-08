package com.learn.start.service;

import com.learn.start.config.JWT.JwtUtil;
import com.learn.start.dto.request.LoginDTO_Req;
import com.learn.start.dto.response.LoginDTO_Res;
import com.learn.start.dto.response.UsersDTO_Res;
import com.learn.start.entity.Users;
import com.learn.start.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    public LoginDTO_Res loginUser(LoginDTO_Req request) {

        Optional<UsersDTO_Res>  optionalUser = userService.getUserByGmail(request.getEmail());
        if (optionalUser.isPresent()) {
            UsersDTO_Res userRes = optionalUser.get();

            boolean check = PasswordUtil.verifyPassword(request.getPassword(), userRes.getMk());
            if(!check) {return null;}

            String token = jwtUtil.generateToken(userRes.getId(),request.getEmail(), userRes.getRoleId(), userRes.getRoleName());
            LoginDTO_Res res = new LoginDTO_Res();
            res.setToken(token);
            res.setUsername(userRes.getHoTen());
            return res;
        }
        else {return null;}
    }
}
