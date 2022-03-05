package com.example.demo.dto.owner;

import com.example.demo.model.owner.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class OwnerDto {

    private String name;

    private String email;

    private String password;

    private String phone;

    public Owner newOwner() {
         return Owner.builder()
                 .name(name)
                 .email(email)
                 .password(password)
                 .phone(phone)
                 .build();
    }
}
