package com.example.demo.mapper;

import com.example.demo.dto.address.AddressDto;
import com.example.demo.dto.address.AddressRoadRequest;
import com.example.demo.dto.address.AddressZipCodeRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<AddressDto> selectAllByRoadInfo(AddressRoadRequest roadRequest);

    List<AddressDto> selectAllByZipCode(AddressZipCodeRequest zipCodeRequest);
}
