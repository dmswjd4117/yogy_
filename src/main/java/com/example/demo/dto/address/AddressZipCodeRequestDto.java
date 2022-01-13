package com.example.demo.dto.address;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressZipCodeRequestDto {
    public String zipCode;

    public AddressZipCodeRequestDto(){}

    public AddressZipCodeRequestDto(String zipCode) {
        this.zipCode = zipCode;
    }
}
