package com.example.demo.controller;


import com.example.demo.dto.address.DeliveryLocationDto;
import com.example.demo.service.DeliveryLocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery/location")
public class DeliveryLocationController {

    private final DeliveryLocationService deliveryLocationService;


    public DeliveryLocationController(DeliveryLocationService deliveryLocation) {
        this.deliveryLocationService = deliveryLocation;
    }


    @PostMapping("/add/{storeId}")
    public void AddDeliveryLocation(@RequestBody DeliveryLocationDto deliveryLocation, @PathVariable Long storeId){
        deliveryLocationService.addDeliveryLocation(storeId, deliveryLocation);
    }

}
