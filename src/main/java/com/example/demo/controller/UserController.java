package com.example.demo.controller;

import com.example.demo.annotaion.LoginUserId;
import com.example.demo.dto.user.LoginRequestDto;
import com.example.demo.dto.user.UpdateUserAddressDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.dto.user.UserInfoDto;
import com.example.demo.excpetion.InvalidUserRequestException;
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
    public ApiResult<?> registerUser(@RequestBody  UserDto user)  {

        Long id;

        try {
            id = userService.insertUser(user);
        }catch (InvalidUserRequestException exception){
            return ApiUtils.error(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception){
            return ApiUtils.error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ApiResult<Long>(true, id, null);

    }

    @PatchMapping("/address/update")
    public ApiResult<?> updateAddress(@RequestBody UpdateUserAddressDto updateUserAddressDto, @LoginUserId Long userId){

        try {
            userService.updateAddress(updateUserAddressDto, userId);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }

        return new ApiResult<>(true, updateUserAddressDto, null);
    }


    @PostMapping("/login")
    public ApiResult<?> login(@RequestBody LoginRequestDto requestDto){
        String token;

        try {
            token = userService.loginUser(requestDto);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }

        return new ApiResult<String>(true, token, null);
    }

    @GetMapping("/me")
    public ApiResult<?> getUserInfo(HttpServletRequest req) {
        try{
            UserInfoDto user = userService.getUserInfo(req);
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

