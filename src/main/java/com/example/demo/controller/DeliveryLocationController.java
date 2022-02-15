package com.example.demo.controller;


import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import com.example.demo.service.DeliveryLocationService;
import com.example.demo.utils.ApiUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery/location")
public class DeliveryLocationController {

    private final DeliveryLocationService deliveryLocationService;


    public DeliveryLocationController(DeliveryLocationService deliveryLocation) {
        this.deliveryLocationService = deliveryLocation;
    }

    @GetMapping("/{addressDetail}")
    public ApiUtils.ApiResult<?> searchDeliveryLocation(@PathVariable String addressDetail){
        List<DeliveryLocationDto> list = null;

        try{
            list = deliveryLocationService.searchDeliveryLocation(addressDetail);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }

         return ApiUtils.success(list);
    }

    @PostMapping("/{storeId}")
    public ApiUtils.ApiResult<?> AddDeliveryLocation(@RequestBody DeliveryLocationDto deliveryLocation, @PathVariable Long storeId){
        try {
            deliveryLocationService.addDeliveryLocation(storeId, deliveryLocation);
            return ApiUtils.success(deliveryLocation);
        }catch (Exception exception){
            return ApiUtils.error(exception.getMessage());
        }
    }

}
