package com.example.demo.dao;

import com.example.demo.dto.cart.ItemDto;
import com.example.demo.utils.RedisKeyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;


@Repository
@Slf4j
public class CartDao {
    private final RedisTemplate<String, Object> redisTemplate;

    public CartDao(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public Long getCartSize(Long userId){
        String key = RedisKeyFactory.generateCartKey(userId);
        HashOperations<String, Long, ItemDto> hashOperations = redisTemplate.opsForHash();
        return hashOperations.size(key);
    }

    public void addCartItem(ItemDto item, Long userId){
        String key = RedisKeyFactory.generateCartKey(userId);
        Long field = item.getMenuInfo().getId();
        HashOperations<String, Long, ItemDto> hashOperations = redisTemplate.opsForHash();

        hashOperations.put(key, field, item);
    }

    public List<ItemDto> getItems(Long userId){
        String key = RedisKeyFactory.generateCartKey(userId);
        HashOperations<String, Long, ItemDto> hashOperations = redisTemplate.opsForHash();

        return hashOperations.values(key);
    }

   public void deleteItem(Long menuId, Long userId) {
        String key = RedisKeyFactory.generateCartKey(userId);
        HashOperations<String, Long, ItemDto> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, menuId);
    }

    public void deleteAllItem(Long userId) {
        String key = RedisKeyFactory.generateCartKey(userId);
        HashOperations<String, Long, ItemDto> hashOperations = redisTemplate.opsForHash();

        Iterator<Long> hashKeys = hashOperations.keys(key).iterator();
        while (hashKeys.hasNext()){
            hashOperations.delete(key, hashKeys.next());
        }
    }


    public Long getCurStoreId(Long userId){
        String key = RedisKeyFactory.generateCartStoreKey(userId);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        Object obj = valueOperations.get(key);
        if(obj == null) return null;

        String value = String.valueOf(obj);
        if(value.equals("")) return null;

        return Long.valueOf(value);
    }

    public void setCurStoreId(Long storeId, Long userId){
        String key = RedisKeyFactory.generateCartStoreKey(userId);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        valueOperations.append(key, String.valueOf(storeId));
    }

    public void deleteCurStoreId(Long userId){
        String key = RedisKeyFactory.generateCartStoreKey(userId);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, "");
    }

}

/*
* hash
* https://docs.spring.io/spring-data/redis/docs/current/api/org/springframework/data/redis/core/HashOperations.html
* */
