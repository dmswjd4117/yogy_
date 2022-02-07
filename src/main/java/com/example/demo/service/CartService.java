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
            throw new Exception("장바구니에는 같은 가게의 메뉴만 담을 수 있습니다.");
        }

        cartDao.addCartItem(item, userId);
    }


    public List<ItemDto> getCartItems(Long userId) throws Exception {
        if(userId == null){
            throw new AuthenticationException("로그인해주세요");
        }
        List<ItemDto> list = cartDao.getItems(userId);
        return list;
    }


    public void deleteItem(Long menuId, Long userId) throws AuthenticationException {
        if(userId == null){
            throw new AuthenticationException("로그인해주세요");
        }

        cartDao.deleteItem(menuId, userId);

        if(cartDao.getCartSize(userId).equals(0L)){
            cartDao.deleteCurStoreId(userId);
        }
    }


    public void deleteAllItem(Long userId) throws AuthenticationException {
        if(userId == null){
            throw new AuthenticationException("로그인해주세요");
        }
        cartDao.deleteAllItem(userId);
        cartDao.deleteCurStoreId(userId);
    }
}
