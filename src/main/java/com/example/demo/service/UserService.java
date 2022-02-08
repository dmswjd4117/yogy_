package com.example.demo.service;



import com.example.demo.dao.AuthDao;
import com.example.demo.dto.user.*;
import com.example.demo.excpetion.InvalidUserRequestException;
import com.example.demo.excpetion.TokenException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.AuthorizationExtractor;
import com.example.demo.utils.JwtFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final String USER_TOKEN_KEY = "user_id";
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtFactory jwtFactory;
    private final AuthorizationExtractor authExtractor;
    private final AuthDao authDao;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtFactory jwtFactory, AuthorizationExtractor authExtractor, AuthDao authDao) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtFactory = jwtFactory;
        this.authExtractor = authExtractor;
        this.authDao = authDao;
    }


    public Long insertUser(RegisterRequestDto userDto)  {
        if(RegisterRequestDto.isNull(userDto)){
            throw new InvalidUserRequestException("there is null value in user request");
        }
        if(isEmailExist(userDto.getEmail())){
            throw new InvalidUserRequestException("email already exists");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        userMapper.insertUser(userDto);

        return userDto.getId();
    }

    public String loginUser(LoginRequestDto requestDto){
        if(!isEmailExist(requestDto.getEmail())){
            System.out.println(requestDto.getEmail());
            throw new IllegalArgumentException("email doesn't exist");
        }

        UserDto user = userMapper.findByEmail(requestDto.getEmail());

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("password doesn't match");
        }

        Map<String, Object> payloads = new HashMap<>();
        payloads.put(USER_TOKEN_KEY, user.getId());
        String token = jwtFactory.createToken("user", payloads);
        return token;
    }


    public boolean isEmailExist(String email){
        return userMapper.isEmailExist(email);
    }

    public List<UserDto> getAllUsers() {
        final List<UserDto> userDtoList = userMapper.findAll();
        return userDtoList;
    }

    public Long getCurUserId(HttpServletRequest req) {

        String token = authExtractor.extract(req, "bearer");

        if(Strings.isEmpty(token)){
            throw new IllegalArgumentException("bearer token is empty");
        }

        if(!jwtFactory.validateToken(token)){
            throw new TokenException("invalid token");
        }

        Long user_id = jwtFactory.decodeToken(token, USER_TOKEN_KEY);

        return user_id;
    }

    public UserInfoDto getUserInfo(HttpServletRequest req) {

        Long user_id = getCurUserId(req);

        UserDto user = userMapper.findById(user_id);
        if(user == null){
            throw new TokenException("invalid id");
        }

        UserInfoDto user_info = UserInfoDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .id(user.getId())
                .build();

        return user_info;

    }

    public void logoutUser(String token) throws AuthenticationException {
        authDao.addBlackList(token);
    }
}
