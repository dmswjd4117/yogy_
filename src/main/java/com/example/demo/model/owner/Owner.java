package com.example.demo.model.owner;

import com.example.demo.security.Jwt;
import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

@Builder
public class Owner {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private LocalDateTime createdAt;

    private String CompanyRegistrationNumber;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyRegistrationNumber() {
        return CompanyRegistrationNumber;
    }

    public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
        CompanyRegistrationNumber = companyRegistrationNumber;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
                .append("CompanyRegistrationNumber", CompanyRegistrationNumber)
                .append("password", "[protected]")
                .build();
    }
}
