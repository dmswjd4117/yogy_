package com.example.demo.utils;


import com.example.demo.dto.user.UserDto;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtFactory {
    final String key = "jwt-key";
    final Long tokenValidMs = 60L * 60 * 60 * 10; // 5 hours

    public String createToken(UserDto userDto) {
        // headers
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", userDto.getId());

     
        // token Builder
        Date now = new Date();
        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject("user")
                .setExpiration(new Date(now.getTime() + tokenValidMs))
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact();

        return jwt;
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts
                    .parser()
                    .setSigningKey(key.getBytes())
                    .parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long decodeToken(String token) {

        Jws<Claims> claims = Jwts
                .parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(token);

        Object decoded = claims.getBody().get("id");

        return Long.parseLong(String.valueOf(decoded));

    }


}

