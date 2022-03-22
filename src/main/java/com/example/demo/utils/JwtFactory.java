package com.example.demo.utils;


import com.example.demo.repository.AuthRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtFactory {
    final String JWT_KEY = "jwt-key";
    final Long tokenValidMs = 1000L * 60 * 60 * 1000;
    private final AuthRepository authRepository;

    public String createToken(String subject, Map<String , Object> payloads) {
        // headers
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");


        // token Builder
        Date now = new Date();
        System.out.println(new Date(now.getTime()));
        System.out.println(new Date(now.getTime() + tokenValidMs));

        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject(subject)
                .setExpiration(new Date(now.getTime() + tokenValidMs))
                .signWith(SignatureAlgorithm.HS256, JWT_KEY.getBytes()) // HS256과 Key로 Sign
                .compact();

        return jwt;
    }



    public boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts
                    .parser()
                    .setSigningKey(JWT_KEY.getBytes())
                    .parseClaimsJws(token);

            if(authRepository.isBlackList(token)){
                return false;
            }

            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public long decodeToken(String token, String token_key) {

        Jws<Claims> claims = Jwts
                .parser()
                .setSigningKey(JWT_KEY.getBytes())
                .parseClaimsJws(token);

        Object decoded = claims.getBody().get(token_key);

        return Long.parseLong(String.valueOf(decoded));
    }

}

