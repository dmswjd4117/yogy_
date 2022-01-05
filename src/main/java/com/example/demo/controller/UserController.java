package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiUtils.ApiResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public Long registerUser(@RequestBody  UserDto user){
        Long id = userService.insertUser(user);
        return id;
    }

    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody LoginRequestDto requestDto){
        String token = userService.loginUser(requestDto);
        return new ApiResult<String>(true, token, null);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        final List<UserDto> userDtoList = userService.getAllUsers();
        return userDtoList;
    }

}
