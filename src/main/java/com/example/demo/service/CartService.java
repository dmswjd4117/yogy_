package com.example.demo.service;


import com.example.demo.repository.CartRepository;
import com.example.demo.dto.cart.ItemDto;
import com.example.demo.excpetion.UnauthenticatedException;
import com.example.demo.excpetion.WrongDataException;
import com.example.demo.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void insertCartItem(ItemDto item, Long userId)  {
        if(userId == null){
            throw new UnauthenticatedException(User.class, userId);
        }

        Long curStoreId = cartRepository.getCurStoreId(userId);
        if(curStoreId == null){
            cartRepository.setCurStoreId(item.getStoreInfo().getId(), userId);
        }
        else if(!curStoreId.equals(item.getStoreInfo().getId())){
            throw new WrongDataException(ItemDto.class, item);
        }

        cartRepository.addCartItem(item, userId);
    }


    public List<ItemDto> getCartItems(Long userId)  {
        if(userId == null){
            throw new UnauthenticatedException(User.class, userId);
        }
        List<ItemDto> list = cartRepository.getItems(userId);
        return list;
    }


    public void deleteItem(Long menuId, Long userId){
        if(userId == null){
            throw new UnauthenticatedException(User.class, userId);
        }

        cartRepository.deleteItem(menuId, userId);

        if(cartRepository.getCartSize(userId).equals(0L)){
            cartRepository.deleteCurStoreId(userId);
        }
    }


    public void deleteAllItem(Long userId){
        if(userId == null){
            throw new UnauthenticatedException(User.class, userId);
        }
        cartRepository.deleteAllItem(userId);
        cartRepository.deleteCurStoreId(userId);
    }
}
