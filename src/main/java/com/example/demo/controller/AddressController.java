package com.example.demo.controller;

import com.example.demo.dto.address.AddressDto;
import com.example.demo.dto.address.AddressRoadRequest;
import com.example.demo.dto.address.AddressZipCodeRequest;
import com.example.demo.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/search/road")
    public List<AddressDto> getAddressByRoadInfo(@RequestBody AddressRoadRequest request){
        List<AddressDto> address_list = addressService.getAddressByRoadInfo(request);
        return address_list;
    }

    @PostMapping("/search/zipCode")
    public List<AddressDto> getAddressByZipcode(@RequestBody AddressZipCodeRequest request){
        System.out.println(request.getZipCode());
        List<AddressDto> address_list = addressService.getAddressByZipcode(request);
        return address_list;
    }

}
