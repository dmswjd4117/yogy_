package com.example.demo.controller;


import com.example.demo.annotaion.LoginOwnerId;
import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import com.example.demo.model.deliveryLocation.DeliveryLocation;
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
         return ApiUtils.success(
                 deliveryLocationService.searchDeliveryLocation(addressDetail)
         );
    }

    @PostMapping("/{storeId}")
    public ApiUtils.ApiResult<?> AddDeliveryLocation(
            @RequestBody DeliveryLocationDto deliveryLocation,
            @PathVariable Long storeId,
            @LoginOwnerId Long ownerId){
        deliveryLocationService.addDeliveryLocation(storeId, deliveryLocation, ownerId);
        return ApiUtils.success(deliveryLocation);
    }

}
