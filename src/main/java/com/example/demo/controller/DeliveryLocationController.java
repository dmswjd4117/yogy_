package com.example.demo.controller;


import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import com.example.demo.security.JwtAuthentication;
import com.example.demo.service.DeliveryLocationService;
import com.example.demo.utils.ApiUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/delivery/location")
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
            @AuthenticationPrincipal JwtAuthentication authentication){
        deliveryLocationService.addDeliveryLocation(storeId, deliveryLocation, authentication.getId());
        return ApiUtils.success(deliveryLocation);
    }

}
