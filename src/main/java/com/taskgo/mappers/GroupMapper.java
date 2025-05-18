package com.taskgo.mappers;

import com.taskgo.dtos.groups.GroupCreateRequestDTO;
import com.taskgo.dtos.groups.GroupUpdateRequestDTO;
import com.taskgo.entities.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group toEntity(GroupCreateRequestDTO groupCreateRequestDTO);

    Group toEntity(GroupUpdateRequestDTO groupUpdateRequestDTO);
}