package com.example.demo.dto.address;

import lombok.Getter;

@Getter
public class DeliveryLocationDto {
    // 법정동코드(10)
    String addressCode;
    // 시 군 구 동
    String addressDetail;
    // 상태
    String status;
}

/*
1100000000	서울특별시	존재
1111000000	서울특별시 종로구	존재
*/