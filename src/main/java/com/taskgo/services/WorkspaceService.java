package com.taskgo.services;

import com.taskgo.dtos.workspaces.WorkspaceCreateRequestDTO;
import com.taskgo.dtos.workspaces.WorkspaceUpdateRequestDTO;
import com.taskgo.entities.Workspace;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.mappers.WorkspaceMapper;
import com.taskgo.repositories.UserRepository;
import com.taskgo.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceMapper workspaceMapper;

    public void createWorkspace(WorkspaceCreateRequestDTO workspaceCreateRequestDTO) throws NoUsersFoundException {
        log.info("Creating workspace: {}", workspaceCreateRequestDTO);
        Workspace workspace = workspaceMapper.toEntity(workspaceCreateRequestDTO);
        workspace.setOwner(userRepository.findById(workspaceCreateRequestDTO.getOwnerId()).orElseThrow(NoUsersFoundException::new));
        workspaceRepository.save(workspace);
        log.info("Workspace created successfully!");
    }

    public void updateWorkspace(String workspaceId, WorkspaceUpdateRequestDTO workspaceUpdateRequestDTO) throws NoWorkspacesFoundException {
        log.info("Updating workspace {}: {}", workspaceId, workspaceUpdateRequestDTO);
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(NoWorkspacesFoundException::new);
        if (workspaceUpdateRequestDTO.getName() != null) {
            workspace.setName(workspaceUpdateRequestDTO.getName());
        }
        if (workspaceUpdateRequestDTO.getDescription() != null) {
            workspace.setDescription(workspaceUpdateRequestDTO.getDescription());
        }
        workspaceRepository.save(workspace);
        log.info("Workspace updated successfully!");
    }

    public void deleteWorkspace(String workspaceId) throws NoWorkspacesFoundException {
        log.info("Deleting workspace {}", workspaceId);
        if (!workspaceRepository.existsById(workspaceId)) {
            log.error("Workspace {} is not found!", workspaceId);
            throw new NoWorkspacesFoundException();
        }
        workspaceRepository.deleteById(workspaceId);
        log.info("Workspace deleted successfully!");
    }
}