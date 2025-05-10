package com.taskgo.mappers;

import com.taskgo.dtos.addresses.AddressDTO;
import com.taskgo.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toEntity (AddressDTO addressDTO);
    AddressDTO toDTO (Address address);
}
