package com.example.demo.controller;

import com.example.demo.annotaion.LoginUserId;
import com.example.demo.dto.user.*;
import com.example.demo.excpetion.NotFoundException;
import com.example.demo.model.user.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiUtils;
import com.example.demo.utils.ApiUtils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/register")
    public ApiResult<Long> registerUser(@RequestBody RegisterRequestDto  user)  {
        return ApiUtils.success( userService.insertUser(user.getEmail(), user.getPassword(), user.getName(), user.getPhone()) );
    }



    @PostMapping("/login")
    public ApiResult<?> login(@RequestBody LoginRequestDto requestDto){
        return ApiUtils.success(userService.loginUser(requestDto.getEmail(), requestDto.getPassword()));
    }

    @GetMapping("/me")
    public ApiResult<?> getUserInfo(HttpServletRequest req) {
        return ApiUtils.success(
                userService.getUser(req)
                        .map(UserDto::of)
                        .orElseThrow(()->new NotFoundException(User.class))

        );
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/check")
    public Long authUser(@LoginUserId Long userId){
        if(userId == null)return null;
        return userId;
    }

    @PostMapping("/logout")
    public ApiResult<?> logoutUser(@RequestBody LogoutRequestDto requestDto){
        String token = requestDto.getToken();

        userService.logoutUser(token);

        return ApiUtils.success(token);
    }

    @PostMapping("/update/info")
    public ApiResult<?> updateUserInfo(@LoginUserId Long userId, @RequestBody UpdateUserInfoRequest requestDto){
        userService.updateUserInfo(userId, requestDto);
        return ApiUtils.success(userId);
    }
}

