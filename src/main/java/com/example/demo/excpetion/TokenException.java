package com.example.demo.excpetion;

public class TokenException extends RuntimeException{
    public TokenException(String message){
        super(message);
    }
}
