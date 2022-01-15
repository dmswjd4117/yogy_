package com.example.demo.service;


import com.example.demo.dto.owner.OwnerDto;
import com.example.demo.dto.owner.OwnerInfoDto;
import com.example.demo.dto.owner.OwnerLoginRequestDto;
import com.example.demo.excpetion.TokenException;
import com.example.demo.mapper.OwnerMapper;
import com.example.demo.utils.AuthorizationExtractor;
import com.example.demo.utils.JwtFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OwnerService {

    private final String OWNER_TOKEN_KEY = "owner_id";
    private final OwnerMapper ownerMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtFactory jwtFactory;
    private final AuthorizationExtractor authorizationExtractor;

    public OwnerService(OwnerMapper ownerMapper, PasswordEncoder passwordEncoder, JwtFactory jwtFactory, AuthorizationExtractor authorizationExtractor) {
        this.ownerMapper = ownerMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtFactory = jwtFactory;
        this.authorizationExtractor = authorizationExtractor;
    }

    public Long insertOwner(OwnerDto ownerDto) {
        if(OwnerDto.isNull(ownerDto)){
            log.error(ownerDto.getName()+" | "+ownerDto.getPhone()+" | "+ownerDto.getEmail()+" | "+ownerDto.getPassword());
            throw new IllegalArgumentException("illegal argument");
        }
        if(isDuplicatedEmail(ownerDto.getEmail())){
            throw new IllegalArgumentException("duplicated email");
        }

        String encodedPassword = passwordEncoder.encode(ownerDto.getPassword());

        ownerDto.setCreatedAt(LocalDateTime.now());
        ownerDto.setPassword(encodedPassword);

        ownerMapper.insertOwner(ownerDto);

        return ownerDto.getId();
    }

    public boolean isDuplicatedEmail(String email){
        return ownerMapper.isDuplicatedEmail(email);
    }

    public String loginOwner(OwnerLoginRequestDto requestDto) {

        OwnerDto owner = ownerMapper.findByEmail(requestDto.getEmail());
        if(owner == null){
            throw new IllegalArgumentException("email doesn't exist.");
        }

        if(!passwordEncoder.matches(requestDto.getPassword(), owner.getPassword())){
            throw new IllegalArgumentException("password doesn't match");
        }

        //payload
        Map<String, Object> payloads = new HashMap<>();
        payloads.put(OWNER_TOKEN_KEY, owner.getId());
        String token = jwtFactory.createToken("owner", payloads);

        return token;
    }

    public OwnerInfoDto getOwnerInfo(HttpServletRequest req) {
        Long ownerId = getCurOwnerId(req);

        OwnerInfoDto ownerInfoDto = ownerMapper.getOwnerInfo(ownerId);

        return ownerInfoDto;
    }


    public Long getCurOwnerId(HttpServletRequest req){
        String tokenType = "adminkey";

        String token = authorizationExtractor.extract(req, tokenType);

        if(Strings.isEmpty(token)){
            throw new IllegalArgumentException("adminKey token is empty.");
        }

        if(!jwtFactory.validateToken(token)){
            throw new TokenException("invalid token");
        }

        Long ownerId = jwtFactory.decodeToken(token, OWNER_TOKEN_KEY);

        return ownerId;
    }

}
