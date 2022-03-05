package com.example.demo.dto.owner;

import lombok.Getter;


public class OwnerLoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
