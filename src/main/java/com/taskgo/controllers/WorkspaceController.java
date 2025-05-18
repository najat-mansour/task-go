package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.workspaces.WorkspaceCreateRequestDTO;
import com.taskgo.dtos.workspaces.WorkspaceUpdateRequestDTO;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.services.WorkspaceService;
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
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @PostMapping(URLs.WORKSPACES_CREATE)
    public ResponseEntity<MessageResponseDTO> createWorkspace(@RequestBody @Valid WorkspaceCreateRequestDTO workspaceCreateRequestDTO) throws NoUsersFoundException {
        log.info("Creating workspace: {}", workspaceCreateRequestDTO);
        workspaceService.createWorkspace(workspaceCreateRequestDTO);
        log.info("Workspace created successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Workspace created successfully!"));
    }

    @PatchMapping(URLs.WORKSPACES_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateWorkspace(@PathVariable String workspaceId, @RequestBody @Valid WorkspaceUpdateRequestDTO workspaceUpdateRequestDTO) throws NoWorkspacesFoundException {
        log.info("Updating workspace {}: {}", workspaceId, workspaceUpdateRequestDTO);
        workspaceService.updateWorkspace(workspaceId, workspaceUpdateRequestDTO);
        log.info("Workspace updated successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Workspace updated successfully!"));
    }

    @DeleteMapping(URLs.WORKSPACES_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteWorkspace(@PathVariable String workspaceId) throws NoWorkspacesFoundException {
        log.info("Deleting workspace {}", workspaceId);
        workspaceService.deleteWorkspace(workspaceId);
        log.info("Workspace deleted successfully!");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("Workspace deleted successfully!"));
    }
}