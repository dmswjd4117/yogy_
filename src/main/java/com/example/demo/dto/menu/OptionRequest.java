package com.example.demo.dto.menu;


import com.example.demo.model.menu.Option;
import lombok.Getter;

@Getter
public class OptionRequest {

     private String name;
    private Long menuId;
    private Long price;

    public Option newOption() {
        return Option.builder()
                .name(name)
                .menuId(menuId)
                .price(price)
                .build();
    }
}
