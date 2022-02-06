package com.example.demo.utils;

import org.springframework.context.annotation.Bean;

public class RedisKeyFactory {

    public static final String CART_KEY = "cart";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String CART_STORE_KEY = "cart_store_key";

    private static String generateKey(String s, String key) {
        return key+":"+s;
    }

    public static String generateCartKey(Long userId){
        return generateKey(CART_KEY, userId+"");
    }

    public static String generateCartStoreKey(Long userId){
        return generateKey(CART_STORE_KEY, userId+"");
    }

    public static String getRefreshToken(String token){
        return generateKey(REFRESH_TOKEN, token);
    }

}
