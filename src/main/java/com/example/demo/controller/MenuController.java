package com.example.demo.controller;

 import com.example.demo.dto.menu.GroupMenuRequest;
import com.example.demo.dto.menu.MenuRequest;
import com.example.demo.dto.menu.OptionRequest;
import com.example.demo.model.menu.GroupMenu;
import com.example.demo.model.menu.Menu;
import com.example.demo.model.menu.Option;
import com.example.demo.security.JwtAuthentication;
import com.example.demo.service.MenuService;
import com.example.demo.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/menu_info")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // group
    @PostMapping("/group")
    ApiUtils.ApiResult<?> insertGroupMenu(@RequestBody GroupMenuRequest groupMenuDto, @AuthenticationPrincipal JwtAuthentication authentication){

        System.out.println(groupMenuDto);

        menuService.insertGroupMenu(groupMenuDto.newGroupMenu(), authentication.getId());

        return ApiUtils.success(groupMenuDto);
    }

    @GetMapping("/group")
    List<GroupMenu> getGroupMenuList(@RequestParam Long storeId){
        return menuService.getGroupMenuList(storeId);
    }

    @DeleteMapping("/group/{groupMenuId}")
    ApiUtils.ApiResult<?> deleteGroupMenu(@PathVariable Long groupMenuId, @AuthenticationPrincipal JwtAuthentication authentication){
        menuService.deleteGroupMenu(groupMenuId, authentication.getId());
        return ApiUtils.success(groupMenuId);
    }

    // menu
    @PostMapping("/menu")
    ApiUtils.ApiResult<?> insertMenu(@RequestBody MenuRequest menuDto, @AuthenticationPrincipal JwtAuthentication authentication){

        menuService.insertMenu(menuDto.newMenu(), authentication.getId());

        return ApiUtils.success(menuDto);
    }

    @GetMapping("/menu")
    List<Menu> getMenuList(@RequestParam Long groupMenuId){
        return menuService.getMenuList(groupMenuId);
    }

    @DeleteMapping("/menu/{menuId}")
    ApiUtils.ApiResult<Long> deleteMenu(@PathVariable Long menuId, @AuthenticationPrincipal JwtAuthentication authentication){
        menuService.deleteMenu(menuId, authentication.getId());
        return ApiUtils.success(menuId);
    }


    // option
    @PostMapping("/option")
    ApiUtils.ApiResult<?> insertOption(@RequestBody OptionRequest optionDto, @AuthenticationPrincipal JwtAuthentication authentication){
        menuService.insertOption(optionDto.newOption(), authentication.getId());
        return ApiUtils.success(optionDto);
    }

    @GetMapping("/option/{menuId}")
    List<Option> getOptionList(@PathVariable Long menuId){
        return menuService.getOptionList(menuId);
    }


}
