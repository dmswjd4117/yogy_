package com.example.demo.mapper;

import com.example.demo.dto.user.UpdateUserAddressDto;
import com.example.demo.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> findAll();

    Long insertUser(UserDto userDto);

    boolean checkUser(String email);

    UserDto findByEmail(String email);

    UserDto findById(Long id);

    void updateAddress(@Param("id") Long id, @Param("buildingManagementNum") String buildingNum, @Param("address") String  address);
}

