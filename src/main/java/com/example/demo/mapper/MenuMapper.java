package com.example.demo.mapper;

import com.example.demo.dto.menu.GroupMenuDto;
import com.example.demo.dto.menu.MenuDto;
import com.example.demo.dto.menu.OptionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    public void insertGroupMenu(GroupMenuDto groupMenuDto);

    void deleteGroupMenu(Long id);

    List<GroupMenuDto> getGroupMenuList(Long storeId);

    void insertMenu(MenuDto menuDto);

    List<MenuDto> getMenuList(Long groupMenuId);

    List<OptionDto> getOptionList(Long menuId);

    void insertOption(OptionDto optionDto);

    void deleteMenu(Long menuId);

    MenuDto getMenu(Long menuId);

    GroupMenuDto getGroupMenu(Long groupMenuId);
}
