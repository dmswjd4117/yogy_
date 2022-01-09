package com.example.demo.dto.address;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressRoadRequest {
    public String roadName;

    public AddressRoadRequest(){};

    public AddressRoadRequest(String roadName) {
        this.roadName = roadName;
    }
}
