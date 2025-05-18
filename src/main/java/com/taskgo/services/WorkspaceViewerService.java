package com.taskgo.services;

import com.taskgo.dtos.workspacesviewers.WorkspaceViewerCreateRequestDTO;
import com.taskgo.entities.WorkspaceViewer;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.exceptions.NoWorkspaceViewerMatchingException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.mappers.WorkspaceViewerMapper;
import com.taskgo.repositories.UserRepository;
import com.taskgo.repositories.WorkspaceRepository;
import com.taskgo.repositories.WorkspaceViewerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class WorkspaceViewerService {
    private final WorkspaceViewerRepository workspaceViewerRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceViewerMapper workspaceViewerMapper;

    public void addViewerToWorkspace(String workspaceId, WorkspaceViewerCreateRequestDTO workspaceViewerCreateRequestDTO) throws NoWorkspacesFoundException, NoUsersFoundException {
        log.info("Adding user {} to workspace {}", workspaceViewerCreateRequestDTO.getViewerId(), workspaceId);
        WorkspaceViewer workspaceViewer = workspaceViewerMapper.toEntity(workspaceViewerCreateRequestDTO);
        workspaceViewer.setWorkspace(workspaceRepository.findById(workspaceId).orElseThrow(NoWorkspacesFoundException::new));
        workspaceViewer.setViewer(userRepository.findById(workspaceViewerCreateRequestDTO.getViewerId()).orElseThrow(NoUsersFoundException::new));
        workspaceViewerRepository.save(workspaceViewer);
        log.info("User added successfully!");
    }

    public void deleteViewerFromWorkspace(String workspaceId, String viewerId) throws NoWorkspaceViewerMatchingException, NoWorkspacesFoundException, NoUsersFoundException {
        log.info("Deleting user {} from workspace {}", viewerId, workspaceId);
        if (!workspaceRepository.existsById(workspaceId)) {
            log.error("Workspace {} is not found!", workspaceId);
            throw new NoWorkspacesFoundException();
        }
        if (!userRepository.existsById(viewerId)) {
            log.error("User {} is not found!", viewerId);
            throw new NoUsersFoundException();
        }
        WorkspaceViewer workspaceViewer = workspaceViewerRepository.findByWorkspaceIdAndViewerId(workspaceId, viewerId).orElseThrow(NoWorkspaceViewerMatchingException::new);
        workspaceViewerRepository.delete(workspaceViewer);
        log.info("User deleted successfully!");
    }
}