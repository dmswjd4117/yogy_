package com.example.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserInfoDto {
    private String email;

    private String phone;

    private String name;

    private String address;

    private String buildingManagementNum;
}