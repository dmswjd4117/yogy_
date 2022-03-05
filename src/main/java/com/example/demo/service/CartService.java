package com.example.demo.service;


import com.example.demo.dao.CartDao;
import com.example.demo.dto.cart.ItemDto;
import com.example.demo.excpetion.AuthenticationException;
import com.example.demo.excpetion.NotFoundException;
import com.example.demo.excpetion.WrongDataException;
import com.example.demo.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartService {

    private final CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void insertCartItem(ItemDto item, Long userId)  {
        if(userId == null){
            throw new AuthenticationException(User.class, userId);
        }

        Long curStoreId = cartDao.getCurStoreId(userId);
        if(curStoreId == null){
            cartDao.setCurStoreId(item.getStoreInfo().getId(), userId);
        }
        else if(!curStoreId.equals(item.getStoreInfo().getId())){
            throw new WrongDataException(ItemDto.class, item);
        }

        cartDao.addCartItem(item, userId);
    }


    public List<ItemDto> getCartItems(Long userId)  {
        if(userId == null){
            throw new AuthenticationException(User.class, userId);
        }
        List<ItemDto> list = cartDao.getItems(userId);
        return list;
    }


    public void deleteItem(Long menuId, Long userId){
        if(userId == null){
            throw new AuthenticationException(User.class, userId);
        }

        cartDao.deleteItem(menuId, userId);

        if(cartDao.getCartSize(userId).equals(0L)){
            cartDao.deleteCurStoreId(userId);
        }
    }


    public void deleteAllItem(Long userId){
        if(userId == null){
            throw new AuthenticationException(User.class, userId);
        }
        cartDao.deleteAllItem(userId);
        cartDao.deleteCurStoreId(userId);
    }
}
