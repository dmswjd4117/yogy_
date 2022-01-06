package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.excpetion.TokenException;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiUtils;
import com.example.demo.utils.ApiUtils.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/me")
    public ApiResult<?> getUserInfo(HttpServletRequest req){
        try{
            UserInfoDto user = userService.getUserInfo(req, "bearer");
            return new ApiResult<>(true, user, null);
        }catch (IllegalArgumentException exception){
            return ApiUtils.error(exception.getMessage(), HttpStatus.NO_CONTENT);
        }catch (TokenException exception){
            return ApiUtils.error(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        final List<UserDto> userDtoList = userService.getAllUsers();
        return userDtoList;
    }

}

// https://gmlwjd9405.github.io/2019/01/28/http-header-types.html