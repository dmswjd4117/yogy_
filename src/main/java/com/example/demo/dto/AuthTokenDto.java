package com.example.demo.dto;


import lombok.Getter;

@Getter
public class AuthTokenDto {

    private String token;

    private AuthTokenDto(String token) {
        this.token = token;
    }

    public static AuthTokenDto of(String token) {
        return new AuthTokenDto(token);
    }

}
