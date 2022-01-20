package com.example.demo.mapper;

import com.example.demo.dto.address.AddressDto;
import com.example.demo.dto.address.AddressResponseDto;
import com.example.demo.dto.address.AddressRoadRequestDto;
import com.example.demo.dto.address.AddressZipCodeRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<AddressResponseDto> searchByRoadName(AddressRoadRequestDto roadRequest);

}
