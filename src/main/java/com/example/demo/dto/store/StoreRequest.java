package com.example.demo.dto.store;

import com.example.demo.model.store.Category;
import com.example.demo.model.store.OpenStatus;
import com.example.demo.model.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoreRequest {

    private String name;
    private String phone;
    private Category category;
    private String address;
    private String buildingManagementNum;
    private String imageUrl;
    private Long ownerId;


    public Store newStore(Long ownerId) {
        return Store.builder()
                .address(address)
                .buildingManagementNum(buildingManagementNum)
                .category(category)
                .imageUrl(imageUrl)
                .phone(phone)
                .name(name)
                .openStatus(OpenStatus.CLOSE)
                .ownerId(ownerId)
                .build();
    }

}
