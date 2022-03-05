package com.example.demo.mapper;

import com.example.demo.model.store.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Mapper
public interface StoreMapper {
    Long insertStore(Store store);

    List<Store> getStoreAll(String addressCode);

    List<Store> getStoreAllByCategoryId(Map<String, Object> map);

    Store getStoreInfo(Long storeId);
}
