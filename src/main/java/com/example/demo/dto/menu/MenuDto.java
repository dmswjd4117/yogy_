package com.example.demo.dto.menu;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {

    private Long id;
    private String name;
    private Long groupMenuId;
    private Long price;
    private String description;
    private String photo;
    private String status;

    public static boolean isNull(MenuDto menuDto){
        if(menuDto.name == null || menuDto.price == null || menuDto.groupMenuId == null){
            return true;
        }
        return false;
    }
}
