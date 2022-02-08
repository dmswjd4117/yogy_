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

    private String image;

}
