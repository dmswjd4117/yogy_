package com.example.demo.controller;

import com.example.demo.dto.address.AddressDto;
import com.example.demo.dto.address.AddressRoadRequest;
import com.example.demo.model.address.Address;
import com.example.demo.service.AddressService;
import com.example.demo.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/road")
    public ApiUtils.ApiResult<List<AddressDto>> getAddressByRoadName(@RequestBody AddressRoadRequest request){
        System.out.println(request);
        List<Address> res = addressService.searchByRoadName(request);

        return ApiUtils.success(
                res.stream()
                        .map(AddressDto::new)
                        .collect(Collectors.toList())
        );
    }
}
