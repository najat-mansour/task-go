package com.taskgo.services;

import com.taskgo.dtos.workspacesviewers.WorkspaceViewerCreateRequestDTO;
import com.taskgo.entities.User;
import com.taskgo.entities.Workspace;
import com.taskgo.entities.WorkspaceViewer;
import com.taskgo.events.ViewersChangedEvent;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.exceptions.NoWorkspaceViewerMatchingException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.mappers.WorkspaceViewerMapper;
import com.taskgo.repositories.UserRepository;
import com.taskgo.repositories.WorkspaceRepository;
import com.taskgo.repositories.WorkspaceViewerRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspaceViewerService {
    private final WorkspaceViewerRepository workspaceViewerRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceViewerMapper workspaceViewerMapper;
    private final EmailSenderService emailSenderService;

    private final ApplicationEventPublisher eventPublisher;

    private void publishViewersChangedEvent(WorkspaceViewer workspaceViewer) {
        eventPublisher.publishEvent(new ViewersChangedEvent(
                workspaceViewer.getViewer().getId(),
                workspaceViewer.getWorkspace().getId()
        ));
    }

    public void addViewerToWorkspace(String workspaceId, WorkspaceViewerCreateRequestDTO workspaceViewerCreateRequestDTO) throws NoWorkspacesFoundException, NoUsersFoundException, MessagingException {
        WorkspaceViewer workspaceViewer = workspaceViewerMapper.toEntity(workspaceViewerCreateRequestDTO);
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(NoWorkspacesFoundException::new);
        User viewer = userRepository.findById(workspaceViewerCreateRequestDTO.getViewerId()).orElseThrow(NoUsersFoundException::new);
        workspaceViewer.setWorkspace(workspace);
        workspaceViewer.setViewer(viewer);
        workspaceViewerRepository.save(workspaceViewer);

        // Notify the viewer via the email
        emailSenderService.sendNotificationEmailWhenViewerAddedToWorkspace(viewer.getEmail());

        publishViewersChangedEvent(workspaceViewer);
    }

    public void deleteViewerFromWorkspace(String workspaceId, String viewerId) throws NoWorkspaceViewerMatchingException, NoWorkspacesFoundException, NoUsersFoundException {
        if (!workspaceRepository.existsById(workspaceId)) {
            throw new NoWorkspacesFoundException();
        }
        if (!userRepository.existsById(viewerId)) {
            throw new NoUsersFoundException();
        }
        WorkspaceViewer workspaceViewer = workspaceViewerRepository.findByWorkspaceIdAndViewerId(workspaceId, viewerId).orElseThrow(NoWorkspaceViewerMatchingException::new);
        workspaceViewerRepository.delete(workspaceViewer);

        publishViewersChangedEvent(workspaceViewer);
    }
}
