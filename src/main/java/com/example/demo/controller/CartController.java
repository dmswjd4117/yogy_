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

    @PostMapping("")
    ApiUtils.ApiResult<?> insertCartItem(@RequestBody ItemDto itemDto, @LoginUserId Long userId){

        cartService.insertCartItem(itemDto, userId);

        return ApiUtils.success(userId);
    }

    @GetMapping("/all")
    ApiUtils.ApiResult<?> getCartItems(@LoginUserId Long userId){
        return ApiUtils.success(
                cartService.getCartItems(userId)
        );
    }


    @DeleteMapping("/{menuId}")
    ApiUtils.ApiResult<?> deleteMenu(@LoginUserId Long userId, @PathVariable Long menuId)   {
        cartService.deleteItem(menuId, userId);
        return ApiUtils.success(menuId);
    }

    @DeleteMapping("/all")
    ApiUtils.ApiResult<?> deleteMenu(@LoginUserId Long userId)   {
        cartService.deleteAllItem(userId);
        return ApiUtils.success(userId);
    }
}





