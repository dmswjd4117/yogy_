package com.example.demo.controller;

import com.example.demo.dto.auth.AuthenticationRequest;
import com.example.demo.excpetion.UnauthenticatedException;
import com.example.demo.model.Role;
import com.example.demo.security.AuthenticationResult;
import com.example.demo.security.JwtAuthenticationToken;
import com.example.demo.utils.ApiUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/user")
    public ApiUtils.ApiResult<AuthenticationResult> authenticateUser(@RequestBody AuthenticationRequest authRequest) {
        try {

            JwtAuthenticationToken authToken = new JwtAuthenticationToken(authRequest.getPrincipal(), authRequest.getCredentials());
            authToken.setRole(Role.USER);

            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ApiUtils.success((AuthenticationResult) authentication.getDetails());

        } catch (AuthenticationException exception){
            throw new UnauthenticatedException(exception.getMessage());
        }
    }

    @PostMapping("/owner")
    public ApiUtils.ApiResult<AuthenticationResult> authenticateOwner(@RequestBody AuthenticationRequest authRequest) {
        try {
            JwtAuthenticationToken authToken = new JwtAuthenticationToken(authRequest.getPrincipal(), authRequest.getCredentials());
            authToken.setRole(Role.OWNER);

            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ApiUtils.success((AuthenticationResult) authentication.getDetails());

        } catch (AuthenticationException exception){
            throw new UnauthenticatedException(exception.getMessage());
        }
    }
}
