package com.example.demo.dto.address;

import com.example.demo.model.address.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class AddressDto {
    // 주소관할읍면동코드
    private String townCode;

    // 시도명
    private String cityName;

    // 시군구명
    private String cityCountyName;

    // 읍면동명
    private String townName;

    // 도로명
    private String roadName;

    // 우편번호
    private String zipCode;

    // 건물관리번호
    private String buildingManagementNum;

    // 시군구용건물명
    private String buildingNameForCity;

    // 상세건물명
    private String detailBuildingName;

    public AddressDto(Address address){
        copyProperties(address, this);
    }

}