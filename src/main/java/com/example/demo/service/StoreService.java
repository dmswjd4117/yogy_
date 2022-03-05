package com.example.demo.service;

import com.example.demo.excpetion.AuthenticationException;
import com.example.demo.mapper.StoreMapper;
import com.example.demo.model.menu.Option;
import com.example.demo.model.owner.Owner;
import com.example.demo.model.store.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class StoreService {

    private final StoreMapper storeMapper;

    public StoreService(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public Long insertStore(Store store, Long ownerId) {
        if(ownerId == null){
            throw new AuthenticationException(Owner.class, ownerId);
        }

        store.setOwnerId(ownerId);
        store.setCreatedAt(LocalDateTime.now());

        storeMapper.insertStore(store);

        return store.getId();
    }


    public Optional<Store> getStoreInfo(Long storeId){
        return Optional.ofNullable(storeMapper.getStoreInfo(storeId));
    }

    public List<Store> searchAllStore(String code) {
        return storeMapper.getStoreAll(code);
    }

    public List<Store> getStoreAllByCategoryId(String addressCode, String categoryId) {

        Map<String, Object> map = new HashMap<>();
        map.put("categoryId", categoryId);
        map.put("addressCode", addressCode);

        return storeMapper.getStoreAllByCategoryId(map);
    }
}
