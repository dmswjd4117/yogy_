package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.security.Jwt;
import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtAuthenticationProvider;
import com.example.demo.service.OwnerService;
import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final Jwt jwt;
    private final JwtTokenConfig jwtTokenConfig;
    private final UserService userService;
    private final OwnerService ownerService;

    public WebSecurityConfig(Jwt jwt, JwtTokenConfig jwtTokenConfig, UserService userService, OwnerService ownerService) {
        this.jwt = jwt;
        this.jwtTokenConfig = jwtTokenConfig;
        this.userService = userService;
        this.ownerService = ownerService;
    }


    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(){
        return new JwtAuthenticationProvider(jwt, userService, ownerService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtTokenConfig.getHeader(), jwt);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()

                .formLogin()
                .disable()

                .headers()
                .disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers("/api/address/**").permitAll()

                .antMatchers("/api/cart/**").hasAnyRole(Role.USER.name())

                .antMatchers(HttpMethod.GET,"/api/delivery/location/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/delivery/location/**").hasAnyRole(Role.OWNER.name())

                .antMatchers(HttpMethod.GET, "/api/menu_info/group").permitAll()
                .antMatchers("/api/menu_info/group/**").hasAnyRole(Role.OWNER.name())

                .antMatchers(HttpMethod.GET, "/api/menu_info/menu").permitAll()
                .antMatchers("/api/menu_info/menu/**").hasAnyRole(Role.OWNER.name())

                .antMatchers(HttpMethod.GET, "/api/menu_info/option").permitAll()
                .antMatchers("/api/menu_info/option/**").hasAnyRole(Role.OWNER.name())

                .antMatchers(HttpMethod.GET, "/api/stores/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/stores").hasAnyRole(Role.OWNER.name())

                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }


}
