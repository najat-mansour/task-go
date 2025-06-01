package com.taskgo.mappers;

import com.taskgo.dtos.users.UserResponseDTO;
import com.taskgo.dtos.workspaces.WorkspaceCreateRequestDTO;
import com.taskgo.dtos.workspaces.WorkspaceResponseDTO;
import com.taskgo.dtos.workspaces.WorkspaceUpdateRequestDTO;
import com.taskgo.entities.User;
import com.taskgo.entities.Workspace;
import com.taskgo.entities.WorkspaceViewer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {
    Workspace toEntity(WorkspaceCreateRequestDTO workspaceCreateRequestDTO);

    Workspace toEntity(WorkspaceUpdateRequestDTO workspaceUpdateRequestDTO);

    @Mapping(target = "viewers", expression = "java(mapViewers(workspace))")
    WorkspaceResponseDTO toResponseDTO(Workspace workspace);

    /**
     * Workspace ==> List<WorkspaceViewer> viewers
     * But
     * WorkspaceResponseDTO ==> List<UserResponseDTO> viewers
     *
     * @param workspace Workspace ==> List<WorkspaceViewer> ==> List<UserResponseDTO>
     * @return List<UserResponseDTO>
     */
    default List<UserResponseDTO> mapViewers(Workspace workspace) {
        if (workspace == null || workspace.getViewers() == null) {
            return null;
        }

        return workspace.getViewers().stream()
                .map(WorkspaceViewer::getViewer)
                .map(this::toUserResponseDTO)
                .toList();
    }
    UserResponseDTO toUserResponseDTO(User user);
}
