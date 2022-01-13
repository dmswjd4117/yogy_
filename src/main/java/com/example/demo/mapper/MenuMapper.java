package com.example.demo.mapper;

import com.example.demo.dto.menu.GroupMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    public void insertGroupMenu(GroupMenuDto groupMenuDto);

    void deleteGroupMenu(Long id);

    List<GroupMenuDto> getGroupMenuList(Long storeId);
}
