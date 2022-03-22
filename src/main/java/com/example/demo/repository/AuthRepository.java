package com.example.demo.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class AuthRepository {

    public static String LOGOUT = "logout";
    private final RedisTemplate<String, Object> redisTemplate;

    public AuthRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addBlackList(String token){
        redisTemplate.opsForValue().append(token, LOGOUT);
    }

    public boolean isBlackList(String token){
        String res = (String) redisTemplate.opsForValue().get(token);
        if(res == null)return false;
        return res.equals(LOGOUT);
    }

}
