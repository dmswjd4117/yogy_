package com.example.demo.service;


import com.example.demo.dto.deliveryLocation.DeliveryLocationDto;
import com.example.demo.excpetion.AuthenticationException;
import com.example.demo.excpetion.DuplicatedException;
import com.example.demo.excpetion.NotFoundException;
import com.example.demo.mapper.DeliveryLocationMapper;
import com.example.demo.mapper.StoreMapper;
import com.example.demo.model.deliveryLocation.DeliveryLocation;
import com.example.demo.model.deliveryLocation.StoreDeliveryLocation;
import com.example.demo.model.owner.Owner;
import com.example.demo.model.store.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryLocationService {

    private final DeliveryLocationMapper deliveryLocationMapper;
    private final StoreMapper storeMapper;

    public DeliveryLocationService(DeliveryLocationMapper deliveryLocationMapper, StoreMapper storeMapper) {
        this.deliveryLocationMapper = deliveryLocationMapper;
        this.storeMapper = storeMapper;
    }

    public void addDeliveryLocation(Long storeId, DeliveryLocationDto deliveryLocation, Long ownerId){

        Store store = storeMapper.getStoreInfo(storeId);
        if(store == null){
            throw new NotFoundException(Store.class, storeId);
        }
        if(!store.getOwnerId().equals(ownerId)){
            throw new AuthenticationException(Owner.class, ownerId, store.getOwnerId());
        }

        List<StoreDeliveryLocation> locationDtoList = getStoreDeliveryLocationsByStoreId(storeId, ownerId);

        boolean isDuplicated = locationDtoList.stream()
                        .anyMatch(locationDto -> locationDto.getAddressCode().equals(deliveryLocation.getAddressCode()));

        if(isDuplicated){
            throw new DuplicatedException(StoreDeliveryLocation.class, storeId, deliveryLocation);
        }

        deliveryLocationMapper.insertLocation(storeId, deliveryLocation.getAddressCode(), deliveryLocation.getAddressDetail());
    }

    public List<DeliveryLocation> searchDeliveryLocation(String addressDetail) {
        return deliveryLocationMapper.getDeliveryLocation(addressDetail.trim());
    }

    public List<StoreDeliveryLocation> getStoreDeliveryLocationsByStoreId(Long storeId, Long ownerId){
        return deliveryLocationMapper.getStoreDeliveryLocationsByStoreId(storeId);
    }

}
