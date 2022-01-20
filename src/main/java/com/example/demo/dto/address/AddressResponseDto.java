package com.example.demo.dto.address;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponseDto {

    String cityName;
    String cityCountyName;
    String townName;
    String roadName;
    String buildingManagementNum;
    String buildingNameForCity;
    String detailBuildingName;

}