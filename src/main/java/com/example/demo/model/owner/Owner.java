package com.example.demo.model.owner;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Owner {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private LocalDateTime createdAt;



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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
