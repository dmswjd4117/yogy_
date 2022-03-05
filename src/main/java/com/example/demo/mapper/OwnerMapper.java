package com.example.demo.mapper;

import com.example.demo.model.owner.Owner;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerMapper {

    Long insertOwner(Owner owner);

    boolean isDuplicatedEmail(String email);

    Owner findByEmail(String email);

    Owner findById(Long ownerId);
}
