package com.example.demo.mapper;

import com.example.demo.model.menu.GroupMenu;
import com.example.demo.model.menu.Menu;
import com.example.demo.model.menu.Option;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    public GroupMenu insertGroupMenu(GroupMenu groupMenu);

    void deleteGroupMenu(Long id);

    List<GroupMenu> getGroupMenuList(Long storeId);

    void insertMenu(Menu menu);

    List<Menu> getMenuList(Long groupMenuId);

    List<Option> getOptionList(Long menuId);

    void insertOption(Option option);

    void deleteMenu(Long menuId);

    Menu getMenu(Long menuId);

    GroupMenu getGroupMenu(Long groupMenuId);
}
