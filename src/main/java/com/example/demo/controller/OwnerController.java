package com.example.demo.controller;

import com.example.demo.dto.owner.OwnerDto;
import com.example.demo.dto.owner.OwnerInfoDto;
import com.example.demo.dto.owner.OwnerLoginRequestDto;
import com.example.demo.excpetion.TokenException;
import com.example.demo.service.OwnerService;
import com.example.demo.utils.ApiUtils;
import com.example.demo.utils.ApiUtils.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @PostMapping("/register")
    public ApiResult<?> registerOwner(@RequestBody OwnerDto owner)  {

        Long id;

        try {
            id = ownerService.insertOwner(owner);
        }catch (IllegalArgumentException exception){
            return ApiUtils.error(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception){
            return ApiUtils.error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ApiResult<Long>(true, id, null);

    }


    @PostMapping("/login")
    public ApiResult<?> login(@RequestBody OwnerLoginRequestDto requestDto){
        String token;

        try {
            token = ownerService.loginOwner(requestDto);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }

        return new ApiResult<String>(true, token, null);
    }

    @GetMapping("/me")
    public ApiResult<?> getOwnerInfo(HttpServletRequest req) {
        try{
            OwnerInfoDto owner = ownerService.getOwnerInfo(req);
            return new ApiResult<>(true, owner, null);
        }catch (IllegalArgumentException exception){
            return ApiUtils.error(exception.getMessage(), HttpStatus.NO_CONTENT);
        }catch (TokenException exception){
            return ApiUtils.error(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
