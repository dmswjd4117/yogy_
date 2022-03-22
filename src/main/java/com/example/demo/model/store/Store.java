package com.example.demo.model.store;

import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;

@ToString
@Builder
public class Store {

    private Long id;
    private String name;
    private String phone;
    private Long ownerId;
    private OpenStatus openStatus;
    private Category category;
    private String address;
    private String buildingManagementNum;
    private LocalDateTime createdAt;
    private String imageUrl;
    private int minimumOrder;
    private int deliveryTip;

    public void setClose(){
        this.openStatus = OpenStatus.CLOSE;
    }

    public void setOpen(){
        this.openStatus = OpenStatus.OPEN;
    }

    public Store(){}

    public Store(Long id, String name, String phone, Long ownerId, OpenStatus openStatus, Category category,
                 String buildingManagementNum, String address, LocalDateTime createdAt, String imageUrl,
                 int minimumOrder, int deliveryTip) {
        checkArgument(name != null, "name must be provided");
        checkArgument(phone != null, "phone must be provided");
        checkArgument(category != null, "category must be provided");
        checkArgument(buildingManagementNum != null, "buildingManagementNum must be provided");
        checkArgument(address != null, "address must be provided");

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.ownerId = ownerId;
        this.openStatus = openStatus;
        this.category = category;
        this.address = address;
        this.buildingManagementNum = buildingManagementNum;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.minimumOrder = minimumOrder;
        this.deliveryTip = deliveryTip;
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

    public Category getCategory() {
        return category;
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
