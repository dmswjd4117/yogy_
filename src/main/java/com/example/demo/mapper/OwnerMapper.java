package com.example.demo.mapper;

import com.example.demo.dto.owner.OwnerDto;
import com.example.demo.dto.owner.OwnerInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerMapper {

    Long insertOwner(OwnerDto owner);

    boolean isDuplicatedEmail(String email);

    OwnerDto findByEmail(String email);

    OwnerInfoDto getOwnerInfo(Long ownerId);
}
