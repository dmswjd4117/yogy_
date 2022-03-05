package com.example.demo.mapper;

import com.example.demo.dto.address.AddressRoadRequest;
import com.example.demo.model.address.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<Address> searchByRoadName(AddressRoadRequest roadRequest);

}
