package com.example.demo.service;

import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import com.example.demo.dto.store.StoreDto;
import com.example.demo.mapper.StoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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


    public StoreDto getStoreInfo(Long storeId){
        return storeMapper.getStoreInfo(storeId);
    }

    public List searchAllStore(String code) {
        return storeMapper.getStoreAll(code);
    }

    public List<StoreDto> getStoreAllByCategoryId(String addressCode, String categoryId) {

        Map<String, Object> map = new HashMap<>();
        map.put("categoryId", categoryId);
        map.put("addressCode", addressCode);

        log.info("categoryId {} addressCode {}", categoryId, addressCode);

        return storeMapper.getStoreAllByCategoryId(map);
    }
}
