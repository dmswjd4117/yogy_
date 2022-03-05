package com.example.demo.model.deliveryLocation;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DeliveryLocation {
    private String addressCode;
    private String addressName;
    private String status;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("addressCode", addressCode)
                .append("addressName", addressName)
                .toString();
    }
}
