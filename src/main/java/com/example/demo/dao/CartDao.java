package com.example.demo.dao;

import com.example.demo.dto.cart.ItemDto;
import com.example.demo.utils.RedisKeyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
@Slf4j
public class CartDao {
    private final RedisTemplate<String, String> redisTemplate;

    public CartDao(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void addCartItem(ItemDto item, Long userId){
        String key = RedisKeyFactory.generateCartKey(userId);
        Long field = item.getMenuInfo().getId();
        HashOperations<String, Long, ItemDto> hashOperations = redisTemplate.opsForHash();


        hashOperations.put(key, field, item);
    }

    public Long getCurStoreId(Long userId){
        String key = RedisKeyFactory.generateCartStoreKey(userId);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String value = valueOperations.get(key);
        if(value == null) return null;
        return Long.parseLong(value);
    }

    public void setCurStoreId(Long storeId, Long userId){
        String key = RedisKeyFactory.generateCartStoreKey(userId);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.append(key, String.valueOf(storeId));
    }

    public List<ItemDto> getItems(Long userId){
        String key = RedisKeyFactory.generateCartKey(userId);
        HashOperations<String, Long, ItemDto> hashOperations = redisTemplate.opsForHash();

        Map<Long, ItemDto> map = hashOperations.entries(key);
        return new ArrayList<>(map.values());
    }


}
