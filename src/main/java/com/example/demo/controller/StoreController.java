package com.example.demo.controller;

import com.example.demo.dto.store.StoreDto;
import com.example.demo.service.StoreService;
import com.example.demo.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/insert")
    public ApiUtils.ApiResult<StoreDto> insertStore(@RequestBody  StoreDto storeDto){

        storeDto.setCreatedAt(LocalDateTime.now());
        storeService.insertStore(storeDto);

        return null;
    }

}
