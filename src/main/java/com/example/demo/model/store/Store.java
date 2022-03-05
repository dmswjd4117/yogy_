package com.example.demo.model.store;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Store {
    enum OpenStatus{
        OPEN, CLOSE
    }

    private Long id;
    private String name;
    private String phone;
    private Long ownerId;
    private Store.OpenStatus openStatus;
    private Long categoryId;
    private String address;
    private String buildingManagementNum;
    private LocalDateTime createdAt;
    private String imageUrl;


    public Store(Long id, String name, String phone, Long ownerId, Store.OpenStatus openStatus, Long categoryId, String buildingManagementNum, String address, LocalDateTime createdAt, String imageUrl) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.ownerId = ownerId;
        this.openStatus = openStatus;
        this.categoryId = categoryId;
        this.address = address;
        this.buildingManagementNum = buildingManagementNum;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public OpenStatus getOpenStatus() {
        return openStatus;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getAddress() {
        return address;
    }

    public String getBuildingManagementNum() {
        return buildingManagementNum;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
