package com.example.demo.service;


import com.example.demo.dto.address.DeliveryLocationDto;
import com.example.demo.mapper.DeliveryLocationMapper;
import org.springframework.stereotype.Service;

@Service
public class DeliveryLocationService {

    private final DeliveryLocationMapper deliveryLocationMapper;

    public DeliveryLocationService(DeliveryLocationMapper deliveryLocationMapper) {
        this.deliveryLocationMapper = deliveryLocationMapper;
    }

    public void addDeliveryLocation(Long storeId, DeliveryLocationDto deliveryLocation){
        deliveryLocationMapper.insertLocation(storeId, deliveryLocation.getAddressCode(), deliveryLocation.getAddressDetail(), deliveryLocation.getStatus());
    }
}
