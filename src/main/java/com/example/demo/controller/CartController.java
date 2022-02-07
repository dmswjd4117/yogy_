package com.example.demo.controller;


import com.example.demo.annotaion.LoginUserId;
import com.example.demo.dto.cart.ItemDto;
import com.example.demo.service.CartService;
import com.example.demo.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    ApiUtils.ApiResult<?> insertCartItem(@RequestBody ItemDto itemDto, @LoginUserId Long userId){

        try{
            cartService.insertCartItem(itemDto, userId);
        }catch (Exception exception){
            exception.printStackTrace();
            return ApiUtils.error(exception.getMessage());
        }

        return ApiUtils.success(userId);
    }

    @GetMapping("/get/all")
    ApiUtils.ApiResult<?> getCartItems(@LoginUserId Long userId){
        List<ItemDto> list;
        try{
            list = cartService.getCartItems(userId);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }

        return ApiUtils.success(list);
    }


    @DeleteMapping("/delete/{menuId}")
    ApiUtils.ApiResult<?> deleteMenu(@LoginUserId Long userId, @PathVariable Long menuId)   {
        try {
            cartService.deleteItem(menuId, userId);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }
        return ApiUtils.success(menuId);
    }

    @DeleteMapping("/delete/all")
    ApiUtils.ApiResult<?> deleteMenu(@LoginUserId Long userId)   {
        try {
            cartService.deleteAllItem(userId);
        }catch (Exception exception){
            exception.printStackTrace();
            return ApiUtils.error(exception.getMessage());
        }
        return ApiUtils.success(userId);
    }
}





