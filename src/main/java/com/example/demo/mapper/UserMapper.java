package com.example.demo.mapper;

import com.example.demo.dto.user.RegisterRequestDto;
import com.example.demo.dto.user.UpdateUserAddressDto;
import com.example.demo.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> findAll();

    Long insertUser(RegisterRequestDto userDto);

    boolean isEmailExist(String email);

    UserDto findByEmail(String email);

    UserDto findById(Long id);

}

