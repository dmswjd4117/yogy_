package com.example.demo.service;


import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import com.example.demo.excpetion.DuplicatedDeliveryLocationException;
import com.example.demo.mapper.DeliveryLocationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryLocationService {

    private final DeliveryLocationMapper deliveryLocationMapper;

    public DeliveryLocationService(DeliveryLocationMapper deliveryLocationMapper) {
        this.deliveryLocationMapper = deliveryLocationMapper;
    }

    public void addDeliveryLocation(Long storeId, DeliveryLocationDto deliveryLocation){
        List<DeliveryLocationDto> locationDtoList = getDeliveryLocationsByStoreId(storeId);

        boolean isDuplicated = locationDtoList.stream()
                        .anyMatch(locationDto -> locationDto.getAddressCode().equals(deliveryLocation.getAddressCode()));

        if(isDuplicated){
            throw new DuplicatedDeliveryLocationException("duplicated location");
        }

        deliveryLocationMapper.insertLocation(storeId, deliveryLocation.getAddressCode(), deliveryLocation.getAddressDetail());
    }

    public List<DeliveryLocationDto> searchDeliveryLocation(String addressDetail) {
        return deliveryLocationMapper.getDeliveryLocation(addressDetail.trim());
    }

    public List<DeliveryLocationDto> getDeliveryLocationsByStoreId(Long storeId){
        return deliveryLocationMapper.getDeliveryLocationsByStoreId(storeId);
    }

}
