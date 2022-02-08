package com.example.demo.dto.store;

import lombok.Getter;

@Getter
public class StoreInfoDto {
    private Long id;
    private String name;
    private String phone;
    private Long categoryId;
    private String address;
    private String buildingManagementNum;
    private String imageUrl;
    private Long minimumOrder;
    private Long deliveryTip;
    private Long ownerId;

}
