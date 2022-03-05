package com.example.demo.dto.store;

import com.example.demo.model.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoreRequest {

    enum OpenStatus{
        OPEN, CLOSE
    }

    private String name;
    private String phone;
    private Long categoryId;
    private String address;
    private String buildingManagementNum;
    private String imageUrl;


    public Store newStore() {
        return Store.builder()
                .address(address)
                .buildingManagementNum(buildingManagementNum)
                .categoryId(categoryId)
                .imageUrl(imageUrl)
                .phone(phone)
                .name(name)
                .build();
    }

}
