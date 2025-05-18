package com.taskgo.mappers;

import com.taskgo.dtos.workspaces.WorkspaceCreateRequestDTO;
import com.taskgo.dtos.workspaces.WorkspaceUpdateRequestDTO;
import com.taskgo.entities.Workspace;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {
    Workspace toEntity(WorkspaceCreateRequestDTO workspaceCreateRequestDTO);

    Workspace toEntity(WorkspaceUpdateRequestDTO workspaceUpdateRequestDTO);
}