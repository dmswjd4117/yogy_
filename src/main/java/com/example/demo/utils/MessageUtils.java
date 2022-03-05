package com.example.demo.utils;

import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

public class MessageUtils {

    private static MessageSourceAccessor messageSourceAccessor;

    public static void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        MessageUtils.messageSourceAccessor = messageSourceAccessor;
    }

    public static String getMessage(String key, Object... params){
        return messageSourceAccessor.getMessage(key, params, Locale.KOREA);
    }

}
