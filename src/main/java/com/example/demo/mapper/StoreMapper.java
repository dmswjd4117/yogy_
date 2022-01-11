package com.example.demo.mapper;

import com.example.demo.dto.store.StoreDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StoreMapper {
    Long insertStore(StoreDto storeDto);
}
