package com.example.demo.dto.address;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressZipCodeRequest {
    public String zipCode;

    public AddressZipCodeRequest(){}

    public AddressZipCodeRequest(String zipCode) {
        this.zipCode = zipCode;
    }
}
