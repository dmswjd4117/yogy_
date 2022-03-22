package com.example.demo.dto.address;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponse {

    private String cityName;
    private String cityCountyName;
    private String townName;
    private String roadName;
    private String buildingManagementNum;
    private String buildingNameForCity;
    private String detailBuildingName;

}