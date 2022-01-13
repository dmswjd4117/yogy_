package com.example.demo.dto.menu;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupMenuDto {
    private Long id;
    private String title;
    private String description;
    private Long storeId;
}
