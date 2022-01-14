package com.example.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String password;

    private String email;

    private String phone;

    private String name;

    private String address;

    private String buildingManagementNum;

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isNull(UserDto userDto){
        if(userDto.password == null || userDto.email == null
                || userDto.phone == null  || userDto.name == null
                || userDto.buildingManagementNum == null || userDto.address == null){
            return true;
        }
        return false;
    }
}
