package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.workspacesviewers.WorkspaceViewerCreateRequestDTO;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.exceptions.NoWorkspaceViewerMatchingException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.services.WorkspaceViewerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLs.WORKSPACES_PREFIX)
@RequiredArgsConstructor
@Tag(name = "4. Workspaces - Viewers")
public class WorkspaceViewerController {
    private final WorkspaceViewerService workspaceViewerService;

    @PostMapping(URLs.WORKSPACES_USERS_CREATE)
    public ResponseEntity<MessageResponseDTO> addViewerToWorkspace(@PathVariable String workspaceId, @RequestBody @Valid WorkspaceViewerCreateRequestDTO workspaceViewerCreateRequestDTO) throws NoWorkspacesFoundException, NoUsersFoundException, MessagingException {
        workspaceViewerService.addViewerToWorkspace(workspaceId, workspaceViewerCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("User added to workspace successfully!"));
    }

    @DeleteMapping(URLs.WORKSPACES_USERS_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteViewerFromWorkspace(@PathVariable String workspaceId, @PathVariable String viewerId) throws NoWorkspacesFoundException, NoUsersFoundException, NoWorkspaceViewerMatchingException {
        workspaceViewerService.deleteViewerFromWorkspace(workspaceId, viewerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("User deleted from workspace successfully!"));
    }
}
