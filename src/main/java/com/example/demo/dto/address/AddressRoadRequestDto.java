package com.example.demo.dto.address;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressRoadRequestDto {
    public String roadName;

    public AddressRoadRequestDto(){};

    public AddressRoadRequestDto(String roadName) {
        this.roadName = roadName;
    }
}
