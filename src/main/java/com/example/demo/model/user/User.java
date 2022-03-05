package com.example.demo.model.user;

import lombok.Builder;

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
}
