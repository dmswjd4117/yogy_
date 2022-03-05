package com.example.demo.dto.address;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponse {

    String cityName;
    String cityCountyName;
    String townName;
    String roadName;
    String buildingManagementNum;
    String buildingNameForCity;
    String detailBuildingName;

}