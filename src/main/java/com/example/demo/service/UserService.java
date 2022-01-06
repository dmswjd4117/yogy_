package com.example.demo.service;


import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.excpetion.TokenException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.AuthorizationExtractor;
import com.example.demo.utils.JwtFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtFactory jwtFactory;
    private final AuthorizationExtractor authExtractor;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtFactory jwtFactory, AuthorizationExtractor authExtractor) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtFactory = jwtFactory;
        this.authExtractor = authExtractor;
    }

    public Long insertUser(UserDto userDto){
        if(checkUser(userDto.getEmail())){
            System.out.println("email already exists");
            return null;
        }

        UserDto encryptedUser = encryptUser(userDto);
        userMapper.insertUser(encryptedUser);
        return encryptedUser.getId();
    }

    public String loginUser(LoginRequestDto requestDto){
        if(!checkUser(requestDto.getEmail())){
            System.out.println("email doesn't exist");
            return null;
        }

        UserDto user = userMapper.findByEmail(requestDto.getEmail());

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            System.out.println("password doesn't match");
            return null;
        }

        String token = jwtFactory.createToken(user);
        return token;
    }


    public boolean checkUser(String email){
        return userMapper.checkUser(email);
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

    public UserInfoDto getUserInfo(HttpServletRequest req, String type) {

        String token = authExtractor.extract(req, type);

        if(Strings.isEmpty(token)){
            throw new IllegalArgumentException("header[Authorization]: bearer token is empty");
        }

        if(!jwtFactory.validateToken(token)){
            throw new TokenException("invalid token");
        }

        Long user_id = jwtFactory.decodeToken(token);
        UserDto user = userMapper.findById(user_id);
        UserInfoDto user_info = UserInfoDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();

        return user_info;

    }
}
