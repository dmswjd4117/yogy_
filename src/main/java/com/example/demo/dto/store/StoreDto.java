package com.example.demo.dto.store;

import com.example.demo.model.store.Store;
import lombok.Builder;

@Builder
public class StoreDto {

    private Long id;
    private String name;
    private String phone;
    private Long ownerId;
    private Long categoryId;
    private String address;
    private String imageUrl;

    public static StoreDto of(Store store){
        return StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .phone(store.getPhone())
                .ownerId(store.getOwnerId())
                .categoryId(store.getCategoryId())
                .address(store.getAddress())
                .imageUrl(store.getImageUrl())
                .build();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
