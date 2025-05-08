package com.learn.start.controller;
import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.GiaoVienDTO_Req;
import com.learn.start.dto.response.ChuongDTO_Res;
import com.learn.start.dto.response.GiaoVienDTO_Res;
import com.learn.start.dto.response.UsersDTO_Res;
import com.learn.start.entity.GiaoVien;
import com.learn.start.response.Res;
import com.learn.start.security.CustomUserDetails;
import com.learn.start.service.GiaoVienService;
import com.learn.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Res<?>> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        int userId = userDetails.getId();
        Optional<UsersDTO_Res> res = userService.getUserById(userId);
        if(res.isPresent()) {
            return new ResponseEntity<>(new Res(true, MessageConstrains.SUCCESS, res.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Res(false, MessageConstrains.LOGIN_FALSE, null), HttpStatus.OK);
    }
}
