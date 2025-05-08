package com.learn.start.config.JWT;

import com.learn.start.security.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "HoangHoangHoangHoangHoangHoang123456";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(int id,String gmail,int idRole, String role) {
        return Jwts.builder()
                .setSubject(gmail)
                .claim("id", id)
                .claim("roleId", idRole)
                .claim("roleName", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*60*60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractGmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public String extractRoleName(String token) {
        Object role =  Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("roleName");
        return role != null ? role.toString() : null;
    }
    public Integer extractid(String token) {
        Object role =  Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("id");
        return role != null ? ((Number) role).intValue() : null;
    }
    public Integer extractRoleId(String token) {
        Object role =  Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("roleId");
        return role != null ? ((Number) role).intValue(): null;
    }

    public CustomUserDetails extractToken(String token) {
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setGmail(extractGmail(token));
        userDetails.setRoleId(extractRoleId(token));
        userDetails.setRoleName(extractRoleName(token));
        userDetails.setId(extractid(token));
        return userDetails;
    }
    //kiểm tra token có hợp lệ không
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
