package com.example.demo.dto.owner;

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
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private LocalDateTime createdAt;

     public static boolean isNull(OwnerDto ownerDto){
         if(ownerDto.name == null || ownerDto.email == null || ownerDto.password == null || ownerDto.phone == null){
             return true;
         }
         return false;
     }
}
