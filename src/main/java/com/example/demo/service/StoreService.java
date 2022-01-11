package com.example.demo.service;

import com.example.demo.dto.store.StoreDto;
import com.example.demo.mapper.StoreMapper;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreMapper storeMapper;

    public StoreService(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public void insertStore(StoreDto storeDto) {
        storeMapper.insertStore(storeDto);
    }
}
