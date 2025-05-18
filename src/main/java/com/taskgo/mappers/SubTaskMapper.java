package com.taskgo.mappers;

import com.taskgo.dtos.subtasks.SubTaskCreateRequestDTO;
import com.taskgo.dtos.subtasks.SubTaskUpdateRequestDTO;
import com.taskgo.entities.SubTask;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubTaskMapper {
    SubTask toEntity(SubTaskCreateRequestDTO subTaskCreateRequestDTO);

    SubTask toEntity(SubTaskUpdateRequestDTO subTaskUpdateRequestDTO);
}