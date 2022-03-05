package com.example.demo.mapper;

import com.example.demo.dto.user.RegisterRequestDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.model.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<User> findAll();

    Long insertUser(User user);

    boolean isEmailExist(String email);

    User findByEmail(String email);

    User findById(Long id);

    void updatePassword(Map<String ,Object> map);

    void updateName(Map<String ,Object> map);

}

