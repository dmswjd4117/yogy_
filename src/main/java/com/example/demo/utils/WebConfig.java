package com.example.demo.utils;

import com.example.demo.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgResolver userArgResolver;
    private final LoginCheckInterceptor loginCheckInterceptor;

    public WebConfig(LoginUserArgResolver userArgResolver, LoginCheckInterceptor loginCheckInterceptor) {
        this.userArgResolver = userArgResolver;
        this.loginCheckInterceptor = loginCheckInterceptor;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
         resolvers.add(userArgResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(loginCheckInterceptor)
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/user/register", "/", "/user/login", "/stores/getAll"
//                );
    }

}