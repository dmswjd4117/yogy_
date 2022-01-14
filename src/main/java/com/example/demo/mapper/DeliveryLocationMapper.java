package com.example.demo.mapper;

import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeliveryLocationMapper {
    void insertLocation(@Param("storeId") Long storeId,
                        @Param("addressCode") String addressCode,
                        @Param("addressDetail") String addressDetail);

    List<DeliveryLocationDto> getDeliveryLocation(String addressDetail);

    List<DeliveryLocationDto> getDeliveryLocationsByStoreId(Long storeId);
}
