package com.taskgo.mappers;

import com.taskgo.dtos.tasks.TaskCreateRequestDTO;
import com.taskgo.dtos.tasks.TaskUpdateRequestDTO;
import com.taskgo.entities.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskCreateRequestDTO taskCreateRequestDTO);

    Task toEntity(TaskUpdateRequestDTO taskUpdateRequestDTO);
}