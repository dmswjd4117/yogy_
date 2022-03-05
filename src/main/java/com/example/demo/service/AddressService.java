package com.example.demo.service;


import com.example.demo.dto.address.AddressRoadRequest;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.model.address.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressMapper addressMapper;

    public AddressService(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public List<Address> searchByRoadName(AddressRoadRequest roadRequest) {
        return addressMapper.searchByRoadName(roadRequest);
    }

}
