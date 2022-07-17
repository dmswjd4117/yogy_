package com.example.demo.dto.address;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class AddressRoadRequest {
    public String cityName;
    public String cityCountyName;
    public String roadName;
    public String buildingNameForCity;
}
