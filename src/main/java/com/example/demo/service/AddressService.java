package com.example.demo.service;


import com.example.demo.dto.address.AddressResponseDto;
import com.example.demo.dto.address.AddressRoadRequestDto;
import com.example.demo.mapper.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressMapper addressMapper;

    public AddressService(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public List<AddressResponseDto> searchByRoadName(AddressRoadRequestDto roadRequest) {
        return addressMapper.searchByRoadName(roadRequest);
    }

}
