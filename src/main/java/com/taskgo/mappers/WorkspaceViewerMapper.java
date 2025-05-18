package com.taskgo.mappers;

import com.taskgo.dtos.workspacesviewers.WorkspaceViewerCreateRequestDTO;
import com.taskgo.entities.WorkspaceViewer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkspaceViewerMapper {
    WorkspaceViewer toEntity(WorkspaceViewerCreateRequestDTO workspaceViewerCreateRequestDTO);
}