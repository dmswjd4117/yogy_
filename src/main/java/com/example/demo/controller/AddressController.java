package com.example.demo.controller;

import com.example.demo.dto.address.AddressRoadRequest;
import com.example.demo.model.address.Address;
import com.example.demo.service.AddressService;
import com.example.demo.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/road")
    public ApiUtils.ApiResult<?> getAddressByRoadName(@RequestBody AddressRoadRequest request){
        return ApiUtils.success(
                addressService.searchByRoadName(request)
        );
    }


}
