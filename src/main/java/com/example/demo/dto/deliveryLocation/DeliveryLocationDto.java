package com.example.demo.dto.deliveryLocation;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class DeliveryLocationDto {
    // 법정동코드(10)
    String addressCode;
    // 시 군 구 동
    String addressDetail;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("addressCode", addressCode)
                .append("addressDetail", addressDetail)
                .toString();
    }
}

/*
1100000000	서울특별시
1111000000	서울특별시 종로구
*/