package com.example.demo.service;



import com.example.demo.repository.AuthRepository;
import com.example.demo.dto.user.*;
import com.example.demo.excpetion.UnauthenticatedException;
import com.example.demo.excpetion.DuplicatedException;
import com.example.demo.excpetion.NotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.user.User;
import com.example.demo.utils.AuthorizationExtractor;
import com.example.demo.utils.JwtFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final String USER_TOKEN_KEY = "user_id";
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtFactory jwtFactory;
    private final AuthorizationExtractor authExtractor;
    private final AuthRepository authRepository;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtFactory jwtFactory, AuthorizationExtractor authExtractor, AuthRepository authRepository) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtFactory = jwtFactory;
        this.authExtractor = authExtractor;
        this.authRepository = authRepository;
    }


    public Long insertUser(String email, String password, String name, String phone)  {

        if(isEmailExist(email)){
            throw new DuplicatedException(User.class, email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .name(name)
                .phone(phone)
                .password(encodedPassword)
                .build();

        userMapper.insertUser(user);

        return user.getId();
    }

    public User loginUser(String email, String password){
        if(!isEmailExist(email)){
            throw new NotFoundException(User.class, email);
        }

        User user = userMapper.findByEmail(email);

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new UnauthenticatedException(User.class, "password");
        }

        return user;
    }


    public boolean isEmailExist(String email){
        return userMapper.isEmailExist(email);
    }

    public List<User> getAllUsers() {
        final List<User> userDtoList = userMapper.findAll();
        return userDtoList;
    }

    public Long getCurUserId(HttpServletRequest req) {

        String token = authExtractor.extract(req, "bearer");

        if(Strings.isEmpty(token)){
            throw new UnauthenticatedException(User.class, "empty token");
        }

        if(!jwtFactory.validateToken(token)){
            throw new UnauthenticatedException(User.class, token);
        }

        Long user_id = jwtFactory.decodeToken(token, USER_TOKEN_KEY);

        return user_id;
    }

    public Optional<User> getUser(HttpServletRequest req) {

        Long user_id = getCurUserId(req);

        User user = userMapper.findById(user_id);

        return Optional.ofNullable(user);
    }

    public void logoutUser(String token){
        authRepository.addBlackList(token);
    }

    public void updateUserInfo(Long userId, UpdateUserInfoRequest requestDto)  {
        if(userId == null) throw  new NotFoundException(User.class, userId);
        if(requestDto.isChangePassword()){
            String curPasswd = requestDto.getCurPassword();
            String newPasswd = requestDto.getNewPassword();

            User user = userMapper.findById(userId);
            if(!passwordEncoder.matches(curPasswd, user.getPassword())){
                throw new UnauthenticatedException(User.class, "password");
            }
            
            String encodedPasswd = passwordEncoder.encode(newPasswd);
            Map<String, Object> passwordMap = new HashMap<>();
            passwordMap.put("password", encodedPasswd);
            passwordMap.put("id", userId);
            userMapper.updatePassword(passwordMap);
        }

        Map<String, Object> nameMap = new HashMap<>();
        nameMap.put("name", requestDto.getName());
        nameMap.put("id", userId);
        userMapper.updateName(nameMap);
    }
}
