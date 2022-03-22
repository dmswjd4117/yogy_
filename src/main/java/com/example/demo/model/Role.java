package com.example.demo.model;

public enum Role {
    OWNER("ROLE_OWNER"), USER("ROLE_USER");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
