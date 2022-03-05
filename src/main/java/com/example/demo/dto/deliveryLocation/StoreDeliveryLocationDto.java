package com.example.demo.dto.deliveryLocation;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StoreDeliveryLocationDto {
    private Long id;
    // 법정동코드(10)
    private String addressCode;
    // 시 군 구 동
    private String addressDetail;
    // 가게 id
    private Long storeId;

    public String getAddressCode() {
        return addressCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("addressCode", addressCode)
                .append("addressDetail", addressDetail)
                .append("storeId", storeId)
                .toString();
    }


}
