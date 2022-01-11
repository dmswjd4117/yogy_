package com.example.demo.excpetion;

public class InvalidUserRequestException extends IllegalArgumentException {
    public InvalidUserRequestException(String message){
        super(message);
    }
}
