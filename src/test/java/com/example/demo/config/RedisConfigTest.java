package com.example.demo.config;

import com.example.demo.dto.cart.ItemDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.utils.RedisKeyFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, ItemDto> redisTemplate;


    public ItemDto getItem(){
        List<ItemDto.OptionInfoDto> optionInfoList = new ArrayList<>();
        ItemDto.OptionInfoDto option1 = ItemDto
                .OptionInfoDto.builder()
                .name("option1")
                .id(1L)
                .price(1000L)
                .build();
        optionInfoList.add(option1);


        ItemDto.MenuInfoDto menuInfo = ItemDto.MenuInfoDto.builder()
                .id(1L)
                .name("menu")
                .price(100000L)
                .build();

        ItemDto.StoreInfoDto storeInfo = ItemDto.StoreInfoDto
                .builder()
                .id(2L)
                .name("shoooppppp")
                .build();

        ItemDto itemDto = ItemDto.builder()
                .count(2)
                .price(10000L)
                .optionList(optionInfoList)
                .menuInfo(menuInfo)
                .storeInfo(storeInfo)
                .build();
        return itemDto;
    }

    @Test
    public void testList(){

        String key = "listKey";

        ItemDto new_item = getItem();

        ListOperations<String, ItemDto> listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key, new_item);


        ItemDto item = (ItemDto) listOperations.index(key, 0);

        log.info("{} {}", item.getMenuInfo().getName(), item.getCount());

    }


    @Test
    void testHash(){

        UserDto userDto = UserDto
                .builder()
                .id(1L)
                .email("1@naver.com")
                .password("ASD")
                .build();


        HashOperations<String, String, ItemDto> hashOperations = redisTemplate.opsForHash();
        String key = RedisKeyFactory.generateCartKey(userDto.getId());
        String field = userDto.getId()+"";
        ItemDto item = getItem();

        hashOperations.put(key, field, item);


        ItemDto get_item = hashOperations.get(key, field);

        log.info("key - field= {}", hashOperations.hasKey(key, field));

        ItemDto.StoreInfoDto storeInfo = get_item.getStoreInfo();
        log.info("store name= {}, store id= {}", storeInfo.getName(), storeInfo.getId());

        log.info("{}", get_item.getMenuInfo().getName());
    }

}

