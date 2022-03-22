package com.example.demo.controller;

import com.example.demo.dto.owner.OwnerDto;
import com.example.demo.dto.owner.OwnerLoginRequest;
import com.example.demo.excpetion.NotFoundException;
import com.example.demo.model.owner.Owner;
import com.example.demo.security.JwtAuthentication;
import com.example.demo.service.OwnerService;
import com.example.demo.utils.ApiUtils;
import com.example.demo.utils.ApiUtils.ApiResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
     }

    @PostMapping("/register")
    public ApiResult<?> registerOwner(@RequestBody OwnerDto ownerDto)  {
        return new ApiResult<Long>(
                true,
                ownerService.insertOwner(ownerDto.newOwner()),
                null);
    }


    @PostMapping("/login")
    public ApiResult<?> login(@RequestBody OwnerLoginRequest requestDto){
        return ApiUtils.success(
                ownerService.loginOwner(requestDto.getEmail(), requestDto.getPassword())
        );
    }

    @GetMapping("/me")
    public ApiResult<?> getOwnerInfo(@AuthenticationPrincipal JwtAuthentication authentication) {
        return ApiUtils.success(
                ownerService.getOwnerInfo(authentication.getId())
                        .orElseThrow(()->new NotFoundException(Owner.class))
        );
    }

}
