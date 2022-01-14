package com.example.demo.utils;


import com.example.demo.annotaion.LoginUserId;
import com.example.demo.service.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class LoginUserArgResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    public LoginUserArgResolver(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(LoginUserId.class);
        boolean IsLongType = Long.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && IsLongType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
        try{
            Long userId = userService.getCurUserId(req);
            return userId;
        }catch (Exception exception){
            log.error(exception.getMessage());
            return null;
        }
    }

}
