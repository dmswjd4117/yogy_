package com.example.demo.mapper;

import com.example.demo.model.deliveryLocation.DeliveryLocation;
import com.example.demo.model.deliveryLocation.StoreDeliveryLocation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeliveryLocationMapper {
    void insertLocation(@Param("storeId") Long storeId,
                        @Param("addressCode") String addressCode,
                        @Param("addressName") String addressName);

    List<DeliveryLocation> getDeliveryLocation(String addressName);

    List<StoreDeliveryLocation> getStoreDeliveryLocationsByStoreId(Long storeId);
}
