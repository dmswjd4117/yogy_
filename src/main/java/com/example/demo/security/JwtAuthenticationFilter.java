package com.example.demo.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class JwtAuthenticationFilter extends GenericFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    private static final Pattern BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
    private static final Pattern ADMIN = Pattern.compile("^adminkey$", Pattern.CASE_INSENSITIVE);

    private final String headerKey;
    private final Jwt jwt;

    public JwtAuthenticationFilter(String headerKey, Jwt jwt) {
        this.headerKey = headerKey;
        this.jwt = jwt;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        try {

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                String token = extractToken(httpServletRequest);
                if (token != null) {
                    Jwt.Claims claims = jwt.verify(token);

                    Long id = claims.id;
                    String email = claims.email;
                    List<GrantedAuthority> authorities = mapAuthorities(claims);

                    JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(new JwtAuthentication(id, email), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }else{
                log.debug("SecurityContextHolder not populated with security token, as it already contained: '{}'",
                        SecurityContextHolder.getContext().getAuthentication());
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }catch (Exception exception){
            handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, null, exception);
        }
    }

    private List<GrantedAuthority> mapAuthorities(Jwt.Claims claims){
        String[] roles = claims.roles;
        if(roles == null || roles.length == 0 ){
            return Collections.emptyList();
        }
        return Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String extractToken(HttpServletRequest request){
        String temp = request.getHeader(headerKey);
        if(temp != null){
            String[] parts = temp.split(" ");
            if(parts.length == 2){
                String type = parts[0];
                String token = parts[1];
                if(BEARER.matcher(type).matches()){
                    return token;
                }
                if(ADMIN.matcher(type).matches()){
                    return token;
                }
            }
        }
        return null;
    }
}


