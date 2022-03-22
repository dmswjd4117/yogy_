package com.example.demo.controller;


import com.example.demo.dto.cart.ItemDto;
import com.example.demo.security.JwtAuthentication;
import com.example.demo.service.CartService;
import com.example.demo.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("")
    ApiUtils.ApiResult<?> insertCartItem(@RequestBody ItemDto itemDto, @AuthenticationPrincipal JwtAuthentication authentication){

        cartService.insertCartItem(itemDto, authentication.getId());

        return ApiUtils.success(authentication.getId());
    }

    @GetMapping("/all")
    ApiUtils.ApiResult<?> getCartItems(@AuthenticationPrincipal JwtAuthentication authentication){
        return ApiUtils.success(
                cartService.getCartItems(authentication.getId())
        );
    }


    @DeleteMapping("/{menuId}")
    ApiUtils.ApiResult<?> deleteMenu(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long menuId)   {
        cartService.deleteItem(menuId, authentication.getId());
        return ApiUtils.success(menuId);
    }

    @DeleteMapping("/all")
    ApiUtils.ApiResult<?> deleteMenu(@AuthenticationPrincipal JwtAuthentication authentication)   {
        cartService.deleteAllItem(authentication.getId());
        return ApiUtils.success(authentication.getId());
    }
}





