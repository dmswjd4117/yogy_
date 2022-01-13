package com.example.demo.service;



import com.example.demo.dto.user.LoginRequestDto;
import com.example.demo.dto.user.UpdateUserAddressDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.dto.user.UserInfoDto;
import com.example.demo.excpetion.InvalidUserRequestException;
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


    public Long insertUser(UserDto userDto)  {
        if(!UserDto.isValidUserDto(userDto)){
            throw new InvalidUserRequestException("invalid user request");
        }
        if(checkUser(userDto.getEmail())){
            throw new InvalidUserRequestException("email already exists");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        userMapper.insertUser(userDto);

        return userDto.getId();
    }

    public String loginUser(LoginRequestDto requestDto){
        if(!checkUser(requestDto.getEmail())){
            throw new IllegalArgumentException("email doesn't exist");
        }

        UserDto user = userMapper.findByEmail(requestDto.getEmail());

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("password doesn't match");
        }

        String token = jwtFactory.createToken(user);
        return token;
    }


    public boolean checkUser(String email){
        return userMapper.checkUser(email);
    }

    public List<UserDto> getAllUsers() {
        final List<UserDto> userDtoList = userMapper.findAll();
        return userDtoList;
    }

    public Long getCurUserId(HttpServletRequest req, String type) {
        String token = authExtractor.extract(req, type);

        if(Strings.isEmpty(token)){
            throw new IllegalArgumentException("bearer token is empty");
        }

        if(!jwtFactory.validateToken(token)){
            throw new TokenException("invalid token");
        }

        Long user_id = jwtFactory.decodeToken(token);

        return user_id;
    }

    public UserInfoDto getUserInfo(HttpServletRequest req, String type) {

        Long user_id = getCurUserId(req, type);

        UserDto user = userMapper.findById(user_id);

        UserInfoDto user_info = UserInfoDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .buildingManagementNum(user.getBuildingManagementNum())
                .build();

        return user_info;

    }

    public void updateAddress(UpdateUserAddressDto updateUserAddressDto, Long userId) {

        if(userId == null){
            throw new TokenException("invalid user");
        }

        String address = updateUserAddressDto.getAddress();
        String buildingManagementNum = updateUserAddressDto.getBuildingManagementNum();

        if(address == null){
            throw new IllegalArgumentException("empty address");
        }
        if(buildingManagementNum == null){
            throw new IllegalArgumentException("empty building management number");
        }

        userMapper.updateAddress(userId,buildingManagementNum,address);
    }

    public String getAddressCode(Long userId){
        return userMapper.getAddressCode(userId);
    }

}
