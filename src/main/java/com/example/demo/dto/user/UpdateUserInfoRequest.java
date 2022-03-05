package com.example.demo.dto.user;


import lombok.Getter;

@Getter
public class UpdateUserInfoRequest {
    private String name;
    private String curPassword;
    private String newPassword;
    private boolean changePassword;
}
