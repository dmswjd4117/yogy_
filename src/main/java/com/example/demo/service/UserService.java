package com.example.demo.service;


import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void insertUser(UserDto userDto){
        UserDto user = encryptUser(userDto);
        userMapper.insertUser(user);
    }

    private UserDto encryptUser(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        UserDto user = UserDto.builder()
                .password(encodedPassword)
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .build();
        return user;
    }

    public List<UserDto> getAllUsers() {
        final List<UserDto> userDtoList = userMapper.findAll();
        return userDtoList;
    }
}
