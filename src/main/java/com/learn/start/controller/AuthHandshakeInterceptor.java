package com.learn.start.controller;

import com.learn.start.config.JWT.JwtUtil;
import com.learn.start.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import org.springframework.http.server.ServletServerHttpRequest;

import java.util.Map;

public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtUtil jwtUtil; // Inject JwtUtil vào đây

    public AuthHandshakeInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpServletRequest req = servletRequest.getServletRequest();

            String token = req.getParameter("token");
            if (token != null && jwtUtil.validateToken(token)) {
                CustomUserDetails userDetails = jwtUtil.extractToken(token);
                // lưu thông tin người dùng vào attributes để lấy ở WebSocketHandler
                attributes.put("userDetails", userDetails);
                return true;
            }
        }
        response.setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
        return false;
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Exception exception) {
        // Không cần xử lý thêm
    }
}
