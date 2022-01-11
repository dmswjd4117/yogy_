package com.example.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserAddressDto {
    private String buildingManagementNum;
    private String address;
}
