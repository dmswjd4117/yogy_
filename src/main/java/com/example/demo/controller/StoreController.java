package com.example.demo.controller;

import com.example.demo.dto.store.StoreDto;
import com.example.demo.dto.store.StoreRequest;
import com.example.demo.excpetion.NotFoundException;
import com.example.demo.model.store.Store;
import com.example.demo.security.JwtAuthentication;
import com.example.demo.service.StoreService;
import com.example.demo.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;


    @PostMapping("")
    public ApiUtils.ApiResult<?> insertStore(@RequestBody StoreRequest storeDto,@AuthenticationPrincipal JwtAuthentication authentication){
        return ApiUtils.success(
                storeService.insertStore(storeDto.newStore(authentication.getId()))
        );
    }

    @GetMapping("/{id}")
    public ApiUtils.ApiResult<StoreDto> searchById(@PathVariable Long id){
        return ApiUtils.success(
                storeService.getStoreInfo(id)
                        .map(StoreDto::of)
                        .orElseThrow(()-> new NotFoundException(Store.class, id))
        );
    }

    @GetMapping("/all")
    public ApiUtils.ApiResult<List<StoreDto>> searchAll(@RequestParam("addressCode") String code){
        return ApiUtils.success(
                storeService.searchAllStore(code).stream()
                        .map(StoreDto::of)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/all/category")
    public ApiUtils.ApiResult<?> searchAllByCategory(@RequestParam("category") String category, @RequestParam("addressCode") String addressCode){
        return ApiUtils.success(
                storeService.getStoreAllByCategoryId(addressCode, category)
                        .stream()
                        .map(StoreDto::of)
                        .collect(Collectors.toList())
        );
    }
}
