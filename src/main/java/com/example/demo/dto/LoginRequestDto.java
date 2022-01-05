package com.example.demo.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {

    public String email;
    public String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
