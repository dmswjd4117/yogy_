package com.example.demo.model.user;

import com.example.demo.security.Jwt;
import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Builder
public class User {
    private Long id;

    private String password;

    private String email;

    private String phone;

    private String name;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String newApiToken(Jwt jwt, String[] roles) {
        Jwt.Claims claims = Jwt.Claims.of(id, email, roles);
        return jwt.newToken(claims);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("email", email)
                .append("name", name)
                .append("phone", phone)
                .append("password", "[protected]")
                .build();
    }
}
