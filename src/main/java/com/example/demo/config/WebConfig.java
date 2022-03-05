package com.example.demo.config;

import com.example.demo.interceptor.LoginCheckInterceptor;
import com.example.demo.utils.LoginOwnerArgsResolver;
import com.example.demo.utils.LoginUserArgResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgResolver userArgResolver;
    private final LoginCheckInterceptor loginCheckInterceptor;
    private final LoginOwnerArgsResolver ownerArgsResolver;

    public WebConfig(LoginUserArgResolver userArgResolver, LoginCheckInterceptor loginCheckInterceptor, LoginOwnerArgsResolver ownerArgsResolver) {
        this.userArgResolver = userArgResolver;
        this.loginCheckInterceptor = loginCheckInterceptor;
        this.ownerArgsResolver = ownerArgsResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
         resolvers.add(userArgResolver);
         resolvers.add(ownerArgsResolver);
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
