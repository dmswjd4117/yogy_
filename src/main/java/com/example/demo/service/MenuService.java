package com.example.demo.service;


import com.example.demo.dto.menu.GroupMenuDto;
import com.example.demo.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public void insertGroupMenu(GroupMenuDto groupMenuDto) {
        menuMapper.insertGroupMenu(groupMenuDto);
    }

    public void deleteGroupMenu(Long groupMenuId) {
        menuMapper.deleteGroupMenu(groupMenuId);
    }

    public List<GroupMenuDto> getGroupMenuList(Long storeId) {
        return menuMapper.getGroupMenuList(storeId);
    }

}
