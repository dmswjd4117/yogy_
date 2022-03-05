package com.example.demo.model.deliveryLocation;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StoreDeliveryLocation {
    private Long id;
    private String addressCode;
    private String addressName;
    private Long storeId;

    public String getAddressCode() {
        return addressCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("addressCode", addressCode)
                .append("addressName", addressName)
                .append("storeId", storeId)
                .toString();
    }

}
