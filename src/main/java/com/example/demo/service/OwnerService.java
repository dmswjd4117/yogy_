package com.example.demo.service;


import com.example.demo.excpetion.UnauthenticatedException;
import com.example.demo.excpetion.DuplicatedException;
import com.example.demo.excpetion.NotFoundException;
import com.example.demo.model.owner.Owner;
import com.example.demo.mapper.OwnerMapper;
import com.example.demo.model.user.User;
import com.example.demo.utils.AuthorizationExtractor;
import com.example.demo.utils.JwtFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public Long insertOwner(Owner owner) {
        if(isDuplicatedEmail(owner.getEmail())){
            throw new DuplicatedException(Owner.class,  owner.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(owner.getPassword());

        owner.setCreatedAt(LocalDateTime.now());
        owner.setPassword(encodedPassword);

        ownerMapper.insertOwner(owner);

        return owner.getId();
    }

    public boolean isDuplicatedEmail(String email){
        return ownerMapper.isDuplicatedEmail(email);
    }

    public Owner loginOwner(String email, String password) {

        Owner owner = ownerMapper.findByEmail(email);
        if(owner == null){
            throw new NotFoundException(User.class, "email doesn't exist.", email);
        }

        if(!passwordEncoder.matches(password, owner.getPassword())){
            throw new UnauthenticatedException(User.class, "password doesn't match");
        }

        return owner;
    }


    public Optional<Owner> getOwnerInfo(Long ownerId) {
        return Optional.ofNullable(
                ownerMapper.findById(ownerId)
        );
    }

    public Long getCurOwnerId(String token){
        if(Strings.isEmpty(token)){
            throw new UnauthenticatedException(Owner.class, "header[adminkey] is empty");
        }

        if(!jwtFactory.validateToken(token)){
            throw new UnauthenticatedException(Owner.class, "invalid token");
        }

        return jwtFactory.decodeToken(token, OWNER_TOKEN_KEY);
    }
}
