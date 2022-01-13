package com.example.demo.controller;

import com.example.demo.dto.menu.GroupMenuDto;
import com.example.demo.service.MenuService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @PostMapping("/{storeId}/group/add")
    void insertGroupMenu(@RequestBody GroupMenuDto groupMenuDto, @PathVariable Long storeId){
        groupMenuDto.setStoreId(storeId);
        menuService.insertGroupMenu(groupMenuDto);
    }

    @DeleteMapping("/{storeId}/group/{groupMenuId}/delete")
    void deleteGroupMenu(@PathVariable Long storeId, @PathVariable Long groupMenuId){
        menuService.deleteGroupMenu(groupMenuId);
    }

    @GetMapping("/{storeId}/group/get")
    List<GroupMenuDto> getGroupMenuList(@PathVariable Long storeId){
        return menuService.getGroupMenuList(storeId);
    }


}
