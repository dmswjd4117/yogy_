package com.example.demo.controller;

import com.example.demo.annotaion.LoginOwnerId;
import com.example.demo.dto.menu.GroupMenuRequest;
import com.example.demo.dto.menu.MenuRequest;
import com.example.demo.dto.menu.OptionRequest;
import com.example.demo.model.menu.GroupMenu;
import com.example.demo.model.menu.Menu;
import com.example.demo.model.menu.Option;
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
    ApiUtils.ApiResult<?> insertGroupMenu(@RequestBody GroupMenuRequest groupMenuDto, @LoginOwnerId Long ownerId){

        System.out.println(groupMenuDto);

        menuService.insertGroupMenu(groupMenuDto.newGroupMenu(), ownerId);

        return ApiUtils.success(groupMenuDto);
    }

    @GetMapping("/group")
    List<GroupMenu> getGroupMenuList(@RequestParam Long storeId){
        return menuService.getGroupMenuList(storeId);
    }

    @DeleteMapping("/group/{groupMenuId}")
    ApiUtils.ApiResult<?> deleteGroupMenu(@PathVariable Long groupMenuId, @LoginOwnerId Long ownerId){
        menuService.deleteGroupMenu(groupMenuId, ownerId);
        return ApiUtils.success(groupMenuId);
    }

    // menu
    @PostMapping("/menu")
    ApiUtils.ApiResult<?> insertMenu(@RequestBody MenuRequest menuDto, @LoginOwnerId Long ownerId){

        menuService.insertMenu(menuDto.newMenu(), ownerId);

        return ApiUtils.success(menuDto);
    }

    @GetMapping("/menu")
    List<Menu> getMenuList(@RequestParam Long groupMenuId){
        return menuService.getMenuList(groupMenuId);
    }

    @DeleteMapping("/menu/{menuId}")
    ApiUtils.ApiResult<Long> deleteMenu(@PathVariable Long menuId, @LoginOwnerId Long ownerId){
        menuService.deleteMenu(menuId, ownerId);
        return ApiUtils.success(menuId);
    }


    // option
    @PostMapping("/option")
    ApiUtils.ApiResult<?> insertOption(@RequestBody OptionRequest optionDto, @LoginOwnerId Long ownerId){
        menuService.insertOption(optionDto.newOption(), ownerId);
        return ApiUtils.success(optionDto);
    }

    @GetMapping("/option/{menuId}")
    List<Option> getOptionList(@PathVariable Long menuId){
        return menuService.getOptionList(menuId);
    }


}
