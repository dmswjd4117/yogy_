package com.example.demo.model.menu;

import lombok.Builder;

@Builder
public class GroupMenu {
    private Long id;
    private String title;
    private String description;
    private Long storeId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getStoreId() {
        return storeId;
    }
}
