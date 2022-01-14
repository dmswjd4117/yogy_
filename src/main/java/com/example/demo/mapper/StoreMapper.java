package com.example.demo.mapper;

import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import com.example.demo.dto.store.StoreDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;


@Mapper
public interface StoreMapper {
    Long insertStore(StoreDto storeDto);

    ArrayList<StoreDto> findByAddressCode(String addressCode);
}
