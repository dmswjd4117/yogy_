package com.example.demo.controller;

import com.example.demo.dto.user.*;
import com.example.demo.excpetion.NotFoundException;
import com.example.demo.model.user.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiUtils;
import com.example.demo.utils.ApiUtils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/register")
    public ApiResult<Long> registerUser(@RequestBody RegisterRequestDto  user)  {
        return ApiUtils.success( userService.insertUser(user.getEmail(), user.getPassword(), user.getName(), user.getPhone()) );
    }


    @GetMapping("/me")
    public ApiResult<?> getUserInfo(HttpServletRequest req) {
        return ApiUtils.success(
                userService.getUser(req)
                        .map(UserDto::of)
                        .orElseThrow(()->new NotFoundException(User.class))

        );
    }

    @PostMapping("/logout")
    public ApiResult<?> logoutUser(@RequestBody LogoutRequestDto requestDto){
        String token = requestDto.getToken();

        userService.logoutUser(token);

        return ApiUtils.success(token);
    }

    @PostMapping("/update/info")
    public ApiResult<?> updateUserInfo(Long userId, @RequestBody UpdateUserInfoRequest requestDto){
        userService.updateUserInfo(userId, requestDto);
        return ApiUtils.success(userId);
    }
}

