package com.example.demo.utils;

public class RedisKeyFactory {

    public static final String CART_KEY = "CART";

    private static String generateKey(String s, String key) {
        return key+":"+s;
    }

    public static String generateCartKey(Long userId){
        return generateKey(CART_KEY, userId+"");
    }

}
