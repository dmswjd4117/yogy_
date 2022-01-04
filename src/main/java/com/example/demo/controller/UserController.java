package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
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
    public void registerUser(@RequestBody  UserDto user){
        userService.insertUser(user);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        final List<UserDto> userDtoList = userService.getAllUsers();
        return userDtoList;
    }



}
