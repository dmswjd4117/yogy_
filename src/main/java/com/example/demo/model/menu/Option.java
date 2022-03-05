package com.example.demo.model.menu;


import lombok.Builder;

@Builder
public class Option {
    private Long id;
    private String name;
    private Long menuId;
    private Long price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getMenuId() {
        return menuId;
    }

}
