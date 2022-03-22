package com.example.demo.service;


import com.example.demo.excpetion.*;
import com.example.demo.model.menu.GroupMenu;
import com.example.demo.model.menu.Menu;
import com.example.demo.model.menu.Option;
import com.example.demo.model.owner.Owner;
import com.example.demo.model.store.Store;
import com.example.demo.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public GroupMenu insertGroupMenu(GroupMenu groupMenu, Long ownerId) {
        checkAuth(ownerId, groupMenu.getStoreId());

        return menuMapper.insertGroupMenu(groupMenu);
    }

    public void deleteGroupMenu(Long groupMenuId, Long ownerId) {

       GroupMenu groupMenu = getGroupMenu(groupMenuId);
        if(groupMenu == null){
            throw new NotFoundException(GroupMenu.class, groupMenuId);
        }
        checkAuth(ownerId, groupMenu.getStoreId());

        menuMapper.deleteGroupMenu(groupMenuId);
    }

    public GroupMenu getGroupMenu(Long groupMenuId){
        return menuMapper.getGroupMenu(groupMenuId);
    }


    public List<GroupMenu> getGroupMenuList(Long storeId) {
        return menuMapper.getGroupMenuList(storeId);
    }

    /*
    * menu
    * */

    public void insertMenu(Menu menu, Long ownerId)  {

        // auth check
       GroupMenu groupMenu = getGroupMenu(menu.getGroupMenuId());
        if(groupMenu == null){
            throw new NotFoundException(GroupMenu.class, menu.getGroupMenuId());
        }
        Long storeId = groupMenu.getStoreId();
        checkAuth(ownerId, storeId);


        // duplication check
        List<Menu> list = getMenuList(menu.getGroupMenuId());
        boolean isDuplicated = list.stream().anyMatch(menuItem -> menuItem.getName().equals(menuItem.getName()));
        if(isDuplicated){
            throw new DuplicatedException(String.valueOf(Menu.class), menu.getName());
        }


        // insert menu
        menuMapper.insertMenu(menu);
    }

    public List<Menu> getMenuList(Long groupId){
        return menuMapper.getMenuList(groupId);
    }


    public void deleteMenu(Long menuId, Long ownerId) {
        Menu menu = getMenuById(menuId);
        if(menu == null){
            throw new NotFoundException(Menu.class, menuId);
        }

       GroupMenu groupMenu = getGroupMenu(menu.getGroupMenuId());
        if(groupMenu == null){
            throw new NotFoundException(GroupMenu.class, menu.getGroupMenuId());
        }

        checkAuth(ownerId, groupMenu.getStoreId());

        menuMapper.deleteMenu(menuId);
    }

    public Menu getMenuById(Long menuId){
        return menuMapper.getMenu(menuId);
    }
    /*
    * option
    * */
    public void insertOption(Option option, Long ownerId) {

        Menu menu = getMenuById(option.getMenuId());
        if(menu == null){
            throw new NotFoundException(Menu.class, option.getMenuId());
        }

        GroupMenu groupMenu = getGroupMenu(menu.getGroupMenuId());
        if(groupMenu == null){
            throw new NotFoundException(GroupMenu.class, menu.getGroupMenuId());
        }

        Long storeId = groupMenu.getStoreId();

        checkAuth(ownerId, storeId);

        menuMapper.insertOption(option);
    }



    public List<Option> getOptionList(Long menuId) {
        return menuMapper.getOptionList(menuId);
    }


    // check owner auth
    public boolean checkAuth(Long ownerId, Long storeId){
        if(ownerId == null){
            throw new UnauthenticatedException(Owner.class, ownerId);
        }

        storeService.getStoreInfo(storeId)
                .map(store -> {
                    if(!ownerId.equals(store.getOwnerId())){
                        throw new UnauthenticatedException(Store.class.getSimpleName(), "ownerId "+ownerId, "storeId "+storeId);
                    }
                    return store;
                })
                .orElseThrow(()-> new NotFoundException(Store.class.getSimpleName(), "ownerId "+ownerId, "storeId "+storeId));

        return true;
    }





}
