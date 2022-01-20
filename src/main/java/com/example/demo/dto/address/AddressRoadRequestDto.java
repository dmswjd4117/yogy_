package com.example.demo.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AddressRoadRequestDto {
    public String cityName;
    public String cityCountyName;
    public String roadName;
    public String buildingNameForCity;
}
