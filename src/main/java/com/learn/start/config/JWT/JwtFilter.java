package com.learn.start.config.JWT;

import com.learn.start.constants.MessageConstrains;
import com.learn.start.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String authHeader = request.getHeader("Authorization");

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                if (jwtUtil.validateToken(token)) {

                    CustomUserDetails userDetails = jwtUtil.extractToken(token);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    writeErrorResponse(response, MessageConstrains.TOKEN_ERR);
                    return;
                }
            } else {
                // Không phải endpoint /auth thì phải có token
                if (!request.getRequestURI().startsWith("/auth")) {
                    System.out.println("khong co token");
                    writeErrorResponse(response, MessageConstrains.LOGIN);
                    return;
                }
            }



        } catch (Exception e) {
            // Bắt lỗi bất ngờ, không throw, mà trả JSON luôn
            System.out.println("khong co token2");
            writeErrorResponse(response, MessageConstrains.ERROR);
        }
        // Nếu không lỗi gì thì tiếp tục
        filterChain.doFilter(request, response);
    }

    private void writeErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String body = String.format("""
            {
                "success": false,
                "message": "%s",
                "data": null,
                "timestamp": "%s"
            }
        """, message, java.time.LocalDateTime.now());

        response.getWriter().write(body);
    }
}
