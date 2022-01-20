package com.example.demo.controller;

import com.example.demo.dto.address.AddressDto;
import com.example.demo.dto.address.AddressResponseDto;
import com.example.demo.dto.address.AddressRoadRequestDto;
import com.example.demo.dto.address.AddressZipCodeRequestDto;
import com.example.demo.service.AddressService;
import com.example.demo.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/search/road")
    public ApiUtils.ApiResult<?> getAddressByRoadName(@RequestBody AddressRoadRequestDto request){
        List<AddressResponseDto> address_list = null;

        try {
            log.info("{} {} {} {}",request.buildingNameForCity, request.cityName, request.cityCountyName, request.roadName);
            address_list = addressService.searchByRoadName(request);

        }catch (Exception exception){
            ApiUtils.error(exception.getMessage());
        }


        return ApiUtils.success(address_list);
    }


}
