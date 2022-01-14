package com.example.demo.excpetion;

public class DuplicatedDeliveryLocationException extends IllegalArgumentException {
    public DuplicatedDeliveryLocationException(String message){
        super(message);
    }
}