package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.workspacesviewers.WorkspaceViewerCreateRequestDTO;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.exceptions.NoWorkspaceViewerMatchingException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.services.WorkspaceViewerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLs.WORKSPACES_PREFIX)
@RequiredArgsConstructor
@Log4j2
public class WorkspaceViewerController {
    private final WorkspaceViewerService workspaceViewerService;

    @PostMapping(URLs.WORKSPACES_USERS_CREATE)
    public ResponseEntity<MessageResponseDTO> addViewerToWorkspace(@PathVariable String workspaceId, @RequestBody @Valid WorkspaceViewerCreateRequestDTO workspaceViewerCreateRequestDTO) throws NoWorkspacesFoundException, NoUsersFoundException {
        log.info("Adding user {} to workspace {}", workspaceViewerCreateRequestDTO.getViewerId(), workspaceId);
        workspaceViewerService.addViewerToWorkspace(workspaceId, workspaceViewerCreateRequestDTO);
        log.info("User added successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("User added to workspace successfully!"));
    }

    @DeleteMapping(URLs.WORKSPACES_USERS_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteViewerFromWorkspace(@PathVariable String workspaceId, @PathVariable String viewerId) throws NoWorkspacesFoundException, NoUsersFoundException, NoWorkspaceViewerMatchingException {
        log.info("Deleting user {} from workspace {}", viewerId, workspaceId);
        workspaceViewerService.deleteViewerFromWorkspace(workspaceId, viewerId);
        log.info("User deleted successfully!");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("User deleted from workspace successfully!"));
    }
}