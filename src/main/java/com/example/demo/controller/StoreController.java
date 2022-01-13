package com.example.demo.controller;

import com.example.demo.annotaion.LoginUserId;
import com.example.demo.dto.store.StoreDto;
import com.example.demo.service.StoreService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;


@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @PostMapping("/insert")
    public ApiUtils.ApiResult<StoreDto> insertStore(@RequestBody  StoreDto storeDto){

        storeDto.setCreatedAt(LocalDateTime.now());
        storeService.insertStore(storeDto);

        return null;
    }

    @GetMapping("/search/all")
    public ArrayList<StoreDto> searchAllByAddressCode(@LoginUserId Long userId){
        String code = userService.getAddressCode(userId);
        return storeService.searchByAddressCode(code);
    }

}
