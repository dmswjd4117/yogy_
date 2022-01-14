package com.example.demo.utils;


import com.example.demo.annotaion.LoginOwnerId;
import com.example.demo.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class LoginOwnerArgsResolver implements HandlerMethodArgumentResolver {

    private final OwnerService ownerService;


    public LoginOwnerArgsResolver(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(LoginOwnerId.class);
        boolean isLong = Long.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && isLong;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();

        try{
            Long ownerId = ownerService.getCurOwnerId(req);
            return ownerId;
        }catch (Exception exception){
            log.error(exception.getMessage());
            return null;
        }
    }
}
