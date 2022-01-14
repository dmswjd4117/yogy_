package com.example.demo.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class StoreDto {
    private Long id;
    private String name;
    private String phone;
    private Long ownerId;
    private Long openStatus;
    private Long categoryId;
    private String address;
    private String buildingManagementNum;
    private LocalDateTime createdAt;


    public StoreDto(Long id, String name, String phone, Long ownerId, Long openStatus, Long categoryId, String buildingManagementNum, String address, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.ownerId = ownerId;
        this.openStatus = openStatus;
        this.categoryId = categoryId;
        this.address = address;
        this.buildingManagementNum = buildingManagementNum;
        this.createdAt = createdAt;
    }

    public static boolean isNull(StoreDto store){
        if(store.name == null || store.phone == null || store.categoryId == null || store.address == null || store.buildingManagementNum == null){
            return true;
        }
        return false;
    }

}
