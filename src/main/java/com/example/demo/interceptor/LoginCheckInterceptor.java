package com.example.demo.interceptor;

import com.example.demo.service.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public LoginCheckInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
        String requestURI = req.getRequestURI();
        log.info("REQUEST URI = {}",requestURI);

        try{
            Long userId = userService.getCurUserId(req);
            return true;
        }catch (Exception exception){
            res.sendRedirect("/user/login");
            return false;
        }

    }

}
