package com.example.demo.dto.menu;

import com.example.demo.model.menu.Menu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequest {

    private String name;
    private Long groupMenuId;
    private Long price;
    private String description;
    private String photo;
    private String status;

    public Menu newMenu(){
        return Menu.builder()
                .name(name)
                .groupMenuId(groupMenuId)
                .description(description)
                .photo(photo)
                .status(status)
                .build();
    }

}
