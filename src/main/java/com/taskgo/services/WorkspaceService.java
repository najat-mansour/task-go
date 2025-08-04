package com.taskgo.services;

import com.taskgo.dtos.workspaces.WorkspaceCreateRequestDTO;
import com.taskgo.dtos.workspaces.WorkspaceResponseDTO;
import com.taskgo.dtos.workspaces.WorkspaceUpdateRequestDTO;
import com.taskgo.entities.Workspace;
import com.taskgo.entities.WorkspaceViewer;
import com.taskgo.events.WorkspacesChangedEvent;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.mappers.WorkspaceMapper;
import com.taskgo.repositories.UserRepository;
import com.taskgo.repositories.WorkspaceRepository;
import com.taskgo.repositories.WorkspaceViewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceViewerRepository workspaceViewerRepository;
    private final WorkspaceMapper workspaceMapper;

    private final ApplicationEventPublisher eventPublisher;

    private void publishWorkspaceChangedEvent(Workspace workspace) {
        eventPublisher.publishEvent(new WorkspacesChangedEvent(
                workspace.getId()
        ));
    }

    public void createWorkspace(WorkspaceCreateRequestDTO workspaceCreateRequestDTO) throws NoUsersFoundException {
        Workspace workspace = workspaceMapper.toEntity(workspaceCreateRequestDTO);
        workspace.setOwner(userRepository.findById(workspaceCreateRequestDTO.getOwnerId()).orElseThrow(NoUsersFoundException::new));
        workspaceRepository.save(workspace);

        publishWorkspaceChangedEvent(workspace);
    }

    public void updateWorkspace(String workspaceId, WorkspaceUpdateRequestDTO workspaceUpdateRequestDTO) throws NoWorkspacesFoundException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(NoWorkspacesFoundException::new);
        if (workspaceUpdateRequestDTO.getName() != null) {
            workspace.setName(workspaceUpdateRequestDTO.getName());
        }
        if (workspaceUpdateRequestDTO.getDescription() != null) {
            workspace.setDescription(workspaceUpdateRequestDTO.getDescription());
        }
        workspaceRepository.save(workspace);

        publishWorkspaceChangedEvent(workspace);
    }

    public void deleteWorkspace(String workspaceId) throws NoWorkspacesFoundException {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(NoWorkspacesFoundException::new);
        workspaceRepository.delete(workspace);

        publishWorkspaceChangedEvent(workspace);
    }

    @Cacheable(value = "workspaces:by-id", key = "#workspaceId")
    public WorkspaceResponseDTO getWorkspaceById(String workspaceId) throws NoWorkspacesFoundException {
        Optional<Workspace> workspace = workspaceRepository.findById(workspaceId);
        return workspace.map(workspaceMapper::toResponseDTO).orElseThrow(NoWorkspacesFoundException::new);
    }

    @Cacheable(value = "workspaces:by-owner-id", key = "#ownerId")
    public List<WorkspaceResponseDTO> getAllWorkspacesByOwnerId(String ownerId) throws NoWorkspacesFoundException, NoUsersFoundException {
        if (!userRepository.existsById(ownerId)) {
            throw new NoUsersFoundException();
        }
        List<Workspace> workspaces = workspaceRepository.findAllByOwnerId(ownerId);
        if (workspaces.isEmpty()) {
            throw new NoWorkspacesFoundException();
        }
        return workspaces.stream().map(workspaceMapper::toResponseDTO).toList();
    }

    @Cacheable(value = "workspaces:by-viewer-id", key = "#viewerId")
    public List<WorkspaceResponseDTO> getAllWorkspacesByViewerId(String viewerId) throws NoWorkspacesFoundException, NoUsersFoundException {
        if (!userRepository.existsById(viewerId)) {
            throw new NoUsersFoundException();
        }
        List<WorkspaceViewer> workspacesViewers = workspaceViewerRepository.findAllByViewerId(viewerId);
        if (workspacesViewers.isEmpty()) {
            throw new NoWorkspacesFoundException();
        }
        return workspacesViewers.stream().map(WorkspaceViewer::getWorkspace).map(workspaceMapper::toResponseDTO).toList();
    }
}
