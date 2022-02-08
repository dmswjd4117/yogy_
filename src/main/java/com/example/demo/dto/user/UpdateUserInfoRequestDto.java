package com.example.demo.dto.user;


import lombok.Getter;

@Getter
public class UpdateUserInfoRequestDto {
    private String name;
    private String curPassword;
    private String newPassword;
    private boolean changePassword;
}
