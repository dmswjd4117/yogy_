package com.example.demo.mapper;

import com.example.demo.dto.address.DeliveryLocationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeliveryLocationMapper {
    void insertLocation(@Param("storeId") Long storeId,
                        @Param("addressCode") String addressCode,
                        @Param("addressDetail") String addressDetail,
                        @Param("status") String status);
}
