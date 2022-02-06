package com.example.demo.service;


import com.example.demo.dao.CartDao;
import com.example.demo.dto.cart.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@Slf4j
@Service
public class CartService {

    private final CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void insertCartItem(ItemDto item, Long userId) throws Exception {
        if(userId == null){
            throw new AuthenticationException("로그인해주세요");
        }

        Long curStoreId = cartDao.getCurStoreId(userId);
        if(curStoreId == null){
            cartDao.setCurStoreId(item.getStoreInfo().getId(), userId);
        }
        else if(!curStoreId.equals(item.getStoreInfo().getId())){
            throw new Exception("다른 가게의 메뉴를 장바구니에 담을 수 없습니다. 현재 가게 id = "+curStoreId);
        }

        cartDao.addCartItem(item, userId);
    }


    public List<ItemDto> getCartItems(Long userId){
        List<ItemDto> list = cartDao.getItems(userId);
        return list;
    }


}
