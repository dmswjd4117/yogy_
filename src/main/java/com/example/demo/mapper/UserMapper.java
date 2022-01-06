package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> findAll();

    Long insertUser(UserDto userDto);

    boolean checkUser(String email);

    UserDto findByEmail(String email);

    UserDto findById(Long id);
}

