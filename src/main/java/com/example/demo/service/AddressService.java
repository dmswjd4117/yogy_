package com.example.demo.service;


import com.example.demo.dto.address.AddressDto;
import com.example.demo.dto.address.AddressRoadRequestDto;
import com.example.demo.dto.address.AddressZipCodeRequestDto;
import com.example.demo.mapper.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressMapper addressMapper;

    public AddressService(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public List<AddressDto> getAddressByRoadInfo(AddressRoadRequestDto roadRequest) {
        return addressMapper.selectAllByRoadInfo(roadRequest);
    }

    public List<AddressDto> getAddressByZipcode(AddressZipCodeRequestDto zipCodeRequest) {
        return addressMapper.selectAllByZipCode(zipCodeRequest);
    }
}
