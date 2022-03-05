package com.example.demo.dto.user;

import com.example.demo.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String email;

    private String phone;

    private String name;

    public static UserDto of(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .name(user.getName())
                .build();
    }
}
