package com.example.demo.security;

import com.example.demo.excpetion.UnauthenticatedException;
import com.example.demo.model.Role;
import com.example.demo.model.owner.Owner;
import com.example.demo.model.user.User;
import com.example.demo.service.OwnerService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final Jwt jwt;
    private final UserService userService;
    private final OwnerService ownerService;

    public JwtAuthenticationProvider(Jwt jwt, UserService userService, OwnerService ownerService) {
        this.jwt = jwt;
        this.userService = userService;
        this.ownerService = ownerService;
    }

    // 여기서 어떻게 유저인지 관리자인지 판단해?
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;

        log.info("jwt provider {} ", authenticationToken);

        try {
            if(authenticationToken.getRole() == null){
                throw new UnauthenticatedException("no author");
            }

            if(authenticationToken.getRole().equals(Role.USER)){
                User user = userService.loginUser(String.valueOf(authenticationToken.getPrincipal()), authenticationToken.getCredentials());

                JwtAuthenticationToken authenticated = new JwtAuthenticationToken(
                        new JwtAuthentication(user.getId(), user.getEmail()), null, createAuthorityList(Role.USER.getValue()));

                String apiToken = user.newApiToken(jwt,  new String[]{Role.USER.getValue()});
                authenticated.setDetails(new AuthenticationResult(apiToken));
                return authenticated;
            }
            if(authenticationToken.getRole().equals(Role.OWNER)){
                Owner owner = ownerService.loginOwner(String.valueOf(authenticationToken.getPrincipal()), authenticationToken.getCredentials());

                JwtAuthenticationToken authenticated = new JwtAuthenticationToken(
                        new JwtAuthentication(owner.getId(), owner.getEmail()), null, createAuthorityList(Role.OWNER.getValue()));

                String apiToken = owner.newApiToken(jwt,  new String[]{Role.OWNER.getValue()});
                authenticated.setDetails(new AuthenticationResult(apiToken));
                return authenticated;
            }

            throw new UnauthenticatedException("no author");

        }catch (Exception exception){
            throw new RuntimeException(exception);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
