package com.taskgo.mappers;


import com.taskgo.dtos.users.UserCreateRequestDTO;
import com.taskgo.dtos.users.UserResponseDTO;
import com.taskgo.dtos.users.UserUpdateRequestDTO;
import com.taskgo.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {
    User toEntity(UserCreateRequestDTO userCreateRequestDTO);

    User toEntity(UserUpdateRequestDTO userUpdateRequestDTO);

    UserResponseDTO toDTO(User user);
}