package com.example.demo.model.menu;

import lombok.Builder;

@Builder
public class Menu {
    private Long id;
    private String name;
    private Long groupMenuId;
    private Long price;
    private String description;
    private String photo;
    private String status;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getGroupMenuId() {
        return groupMenuId;
    }

    public Long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public String getStatus() {
        return status;
    }
}
