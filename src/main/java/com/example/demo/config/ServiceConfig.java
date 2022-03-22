package com.example.demo.config;

import com.example.demo.security.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public Jwt jwt(JwtTokenConfig jwtTokenConfig){
        return new Jwt(jwtTokenConfig.getIssuer(), jwtTokenConfig.getClientSecret(), jwtTokenConfig.getExpirySeconds());
    }
}
