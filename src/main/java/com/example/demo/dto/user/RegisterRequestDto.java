package com.example.demo.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {

    private Long id;

    private String password;

    private String email;

    private String phone;

    private String name;

    public static boolean isNull(RegisterRequestDto userDto) {
        return userDto.password == null || userDto.email == null || userDto.name == null || userDto.phone == null;
    }
}
