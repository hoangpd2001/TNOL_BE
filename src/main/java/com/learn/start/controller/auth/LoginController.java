package com.learn.start.controller.auth;

import com.learn.start.config.JWT.JwtUtil;
import com.learn.start.constants.MessageConstrains;
import com.learn.start.dto.request.LoginDTO_Req;
import com.learn.start.dto.response.JwtResponse;
import com.learn.start.dto.response.LoginDTO_Res;
import com.learn.start.entity.Users;
import com.learn.start.response.Res;
import com.learn.start.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.learn.start.config.VnPayConfig.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public Res login(@RequestBody LoginDTO_Req request) {
        if ("admin".equals(request.getEmail()) && "123".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(1,"admin@gmail.com",3,"AD");
            return new Res(true, MessageConstrains.SUCCESS, new JwtResponse(token, "admin", "AD"));
        } else {
            return new Res(false, MessageConstrains.LOGIN_FALSE, null);
        }
    }
    @PostMapping("/loginUser")
    public Res loginUser(@RequestBody LoginDTO_Req request) {
        LoginDTO_Res res= loginService.loginUser(request);
        if (res != null) {
            return new Res(true, MessageConstrains.SUCCESS, res);
        } else {

            return new Res(false, MessageConstrains.LOGIN_FALSE, null);
        }
    }


}
