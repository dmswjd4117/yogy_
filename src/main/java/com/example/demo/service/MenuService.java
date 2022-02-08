package com.example.demo.service;


import com.example.demo.dto.menu.GroupMenuDto;
import com.example.demo.dto.menu.MenuDto;
import com.example.demo.dto.menu.OptionDto;
import com.example.demo.dto.store.StoreDto;
import com.example.demo.dto.store.StoreInfoDto;
import com.example.demo.excpetion.InvalidUserRequestException;
import com.example.demo.excpetion.TokenException;
import com.example.demo.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Service
public class MenuService {

    private final MenuMapper menuMapper;
    private final StoreService storeService;

    public MenuService(MenuMapper menuMapper, StoreService storeService) {
        this.menuMapper = menuMapper;
        this.storeService = storeService;
    }

    /*
    * group menu
    * */

    public void insertGroupMenu(GroupMenuDto groupMenuDto, Long ownerId) {
        checkAuth(ownerId, groupMenuDto.getStoreId());

        menuMapper.insertGroupMenu(groupMenuDto);
    }

    public void deleteGroupMenu(Long groupMenuId, Long ownerId) {

       GroupMenuDto groupMenu = getGroupMenu(groupMenuId);
        if(groupMenu == null){
            throw new IllegalArgumentException("group menu doesn't exist");
        }
        checkAuth(ownerId, groupMenu.getStoreId());

        menuMapper.deleteGroupMenu(groupMenuId);
    }

    public GroupMenuDto getGroupMenu(Long groupMenuId){
        return menuMapper.getGroupMenu(groupMenuId);
    }


    public List<GroupMenuDto> getGroupMenuList(Long storeId) {
        return menuMapper.getGroupMenuList(storeId);
    }

    /*
    * menu
    * */

    public void insertMenu(MenuDto menuDto, Long ownerId) throws InvalidUserRequestException {
        if(MenuDto.isNull(menuDto)){
            throw new IllegalArgumentException("there is null value in menu request");
        }


        // auth check
       GroupMenuDto groupMenu = getGroupMenu(menuDto.getGroupMenuId());
        if(groupMenu == null){
            throw new IllegalArgumentException("group menu doesn't exist");
        }
        Long storeId = groupMenu.getStoreId();
        checkAuth(ownerId, storeId);


        // duplication check
        List<MenuDto> list = getMenuList(menuDto.getGroupMenuId());
        boolean isDuplicated = list.stream().anyMatch(menu -> menu.getName().equals(menuDto.getName()));
        if(isDuplicated){
            throw new IllegalArgumentException("duplicated menu name");
        }


        // insert menu
        menuMapper.insertMenu(menuDto);
    }

    public List<MenuDto> getMenuList(Long groupId){
        return menuMapper.getMenuList(groupId);
    }


    public void deleteMenu(Long menuId, Long ownerId) {
        MenuDto menu = getMenuById(menuId);
        if(menu == null){
            throw new IllegalArgumentException("menu doesn't exist");
        }

       GroupMenuDto groupMenu = getGroupMenu(menu.getGroupMenuId());
        if(groupMenu == null){
            throw new IllegalArgumentException("group menu doesn't exist");
        }

        checkAuth(ownerId, groupMenu.getStoreId());

        menuMapper.deleteMenu(menuId);
    }

    public MenuDto getMenuById(Long menuId){
        return menuMapper.getMenu(menuId);
    }
    /*
    * option
    * */
    public void insertOption(OptionDto optionDto, Long ownerId) {

        MenuDto menu = getMenuById(optionDto.getMenuId());
        if(menu == null){
            throw new IllegalArgumentException("menu doesn't exist");
        }

        GroupMenuDto groupMenu = getGroupMenu(menu.getGroupMenuId());
        if(groupMenu == null){
            throw new IllegalArgumentException("group menu doesn't exist");
        }

        Long storeId = groupMenu.getStoreId();

        checkAuth(ownerId, storeId);

        menuMapper.insertOption(optionDto);
    }



    public List<OptionDto> getOptionList(Long menuId) {
        return menuMapper.getOptionList(menuId);
    }


    // check owner auth
    public boolean checkAuth(Long ownerId, Long storeId){
        if(ownerId == null){
            throw new TokenException("unauthorized owner access");
        }

        StoreInfoDto storeInfoDto = storeService.getStoreInfo(storeId);
        if(storeInfoDto == null){
            throw new InvalidUserRequestException("store doesnt exist");
        }

        log.info("owner Id : {}, store's owner Id : {}", ownerId, storeInfoDto.getOwnerId());
        if(!storeInfoDto.getOwnerId().equals(ownerId)){
            throw new InvalidUserRequestException("permission error");
        }

        return true;
    }





}
