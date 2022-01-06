package com.example.demo.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class AuthorizationExtractor {

    public String extract(HttpServletRequest req, String type){
        Enumeration<String> headers = req.getHeaders("Authorization");
        while(headers.hasMoreElements()){
            String value = headers.nextElement();
            if(value.toLowerCase().startsWith(type)){
                return value.substring(type.length()).trim();
            }
        }
        return Strings.EMPTY;
    }

}
