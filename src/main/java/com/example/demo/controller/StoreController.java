package com.example.demo.controller;

import com.example.demo.annotaion.LoginOwnerId;
import com.example.demo.annotaion.LoginUserId;
import com.example.demo.dto.store.StoreDto;
import com.example.demo.service.StoreService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;
    private Object List;

    @PostMapping("")
    public ApiUtils.ApiResult<?> insertStore(@RequestBody  StoreDto storeDto, @LoginOwnerId Long ownerId){

        try{
            storeService.insertStore(storeDto, ownerId);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }

        return new ApiUtils.ApiResult<Long>(true, storeDto.getId(), null);
    }


    @GetMapping("/all")
    public ApiUtils.ApiResult<?> searchAll(@RequestParam("addressCode") String code){
        return new ApiUtils.ApiResult<List>(true, storeService.searchAllStore(code), null);
    }

    @GetMapping("/all/category")
    public ApiUtils.ApiResult<?> searchAllByCategory(@RequestParam("id") String categoryId, @RequestParam("addressCode") String addressCode){
        return new ApiUtils.ApiResult<List>(true, storeService.getStoreAllByCategoryId(addressCode, categoryId), null);
    }
}
