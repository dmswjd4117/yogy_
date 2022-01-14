package com.example.demo.service;

import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import com.example.demo.dto.store.StoreDto;
import com.example.demo.mapper.StoreMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private final StoreMapper storeMapper;

    public StoreService(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public Long insertStore(StoreDto storeDto, Long ownerId) {
        if(ownerId == null){
            throw new SecurityException("unauthorized owner access");
        }

        if(StoreDto.isNull(storeDto)){
            throw new IllegalArgumentException("there is null value in store request.");
        }

        storeDto.setOwnerId(ownerId);
        storeDto.setCreatedAt(LocalDateTime.now());

        storeMapper.insertStore(storeDto);

        return storeDto.getId();
    }

    public ArrayList<StoreDto> searchStoreByAddressCode(String code){
        return storeMapper.findByAddressCode(code);
    }

}
