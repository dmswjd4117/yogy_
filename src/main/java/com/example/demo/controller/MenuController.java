package com.example.demo.controller;

import com.example.demo.annotaion.LoginOwnerId;
import com.example.demo.dto.menu.GroupMenuDto;
import com.example.demo.dto.menu.MenuDto;
import com.example.demo.dto.menu.OptionDto;
import com.example.demo.service.MenuService;
import com.example.demo.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menu_info")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // group
    @PostMapping("/group")
    ApiUtils.ApiResult<?> insertGroupMenu(@RequestBody GroupMenuDto groupMenuDto, @LoginOwnerId Long ownerId){
        try {
            menuService.insertGroupMenu(groupMenuDto, ownerId);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }

        return ApiUtils.success(groupMenuDto);
    }

    @GetMapping("/group")
    List<GroupMenuDto> getGroupMenuList(@RequestParam Long storeId){
        return menuService.getGroupMenuList(storeId);
    }

    @DeleteMapping("/group/{groupMenuId}")
    ApiUtils.ApiResult<?> deleteGroupMenu(@PathVariable Long groupMenuId, @LoginOwnerId Long ownerId){
        try{
            menuService.deleteGroupMenu(groupMenuId, ownerId);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }

        return ApiUtils.success(groupMenuId);
    }

    // menu
    @PostMapping("/menu")
    ApiUtils.ApiResult<?> insertMenu(@RequestBody MenuDto menuDto, @LoginOwnerId Long ownerId){

        try{
            menuService.insertMenu(menuDto, ownerId);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }

        return ApiUtils.success(menuDto);
    }

    @GetMapping("/menu")
    List<MenuDto> getMenuList( @RequestParam Long groupMenuId){
        return menuService.getMenuList(groupMenuId);
    }

    @DeleteMapping("/menu/{menuId}")
    ApiUtils.ApiResult deleteMenu(@PathVariable Long menuId, @LoginOwnerId Long ownerId){
        try {
            menuService.deleteMenu(menuId, ownerId);
            return ApiUtils.success(menuId);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }
    }


    // option
    @PostMapping("/option")
    ApiUtils.ApiResult<?> insertOption(@RequestBody OptionDto optionDto, @LoginOwnerId Long ownerId){
        try{
            menuService.insertOption(optionDto, ownerId);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }
        return ApiUtils.success(optionDto);
    }

    @GetMapping("/option/{menuId}")
    List<OptionDto> getOptionList( @PathVariable Long menuId){
        return menuService.getOptionList(menuId);
    }


}
