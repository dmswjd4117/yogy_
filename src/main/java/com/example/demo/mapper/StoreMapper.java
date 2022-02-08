package com.example.demo.mapper;

import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import com.example.demo.dto.store.StoreDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Mapper
public interface StoreMapper {
    Long insertStore(StoreDto storeDto);

    ArrayList<StoreDto> getStoreAll(String addressCode);

    ArrayList<StoreDto> getStoreAllByCategoryId(Map<String, Object> map);

    StoreDto getStoreInfo(Long storeId);
}
