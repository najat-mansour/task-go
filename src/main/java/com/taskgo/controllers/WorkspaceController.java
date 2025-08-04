package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.workspaces.WorkspaceCreateRequestDTO;
import com.taskgo.dtos.workspaces.WorkspaceResponseDTO;
import com.taskgo.dtos.workspaces.WorkspaceUpdateRequestDTO;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.services.WorkspaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLs.WORKSPACES_PREFIX)
@RequiredArgsConstructor
@Tag(name = "3. Workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @PostMapping(URLs.WORKSPACES_CREATE)
    public ResponseEntity<MessageResponseDTO> createWorkspace(@RequestBody @Valid WorkspaceCreateRequestDTO workspaceCreateRequestDTO) throws NoUsersFoundException {
        workspaceService.createWorkspace(workspaceCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Workspace created successfully!"));
    }

    @PatchMapping(URLs.WORKSPACES_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateWorkspace(@PathVariable String workspaceId, @RequestBody @Valid WorkspaceUpdateRequestDTO workspaceUpdateRequestDTO) throws NoWorkspacesFoundException {
        workspaceService.updateWorkspace(workspaceId, workspaceUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Workspace updated successfully!"));
    }

    @DeleteMapping(URLs.WORKSPACES_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteWorkspace(@PathVariable String workspaceId) throws NoWorkspacesFoundException {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("Workspace deleted successfully!"));
    }

    @GetMapping(URLs.WORKSPACES_GET_BY_ID)
    public ResponseEntity<WorkspaceResponseDTO> getWorkspaceById(@PathVariable String workspaceId) throws NoWorkspacesFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(workspaceService.getWorkspaceById(workspaceId));
    }

    @GetMapping(URLs.WORKSPACES_GET_ALL_BY_OWNER_ID)
    public ResponseEntity<List<WorkspaceResponseDTO>> getAllWorkspacesByOwnerId(@PathVariable String ownerId) throws NoWorkspacesFoundException, NoUsersFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(workspaceService.getAllWorkspacesByOwnerId(ownerId));
    }

    @GetMapping(URLs.WORKSPACES_GET_ALL_BY_VIEWER_ID)
    public ResponseEntity<List<WorkspaceResponseDTO>> getAllWorkspacesByViewerId(@PathVariable String viewerId) throws NoWorkspacesFoundException, NoUsersFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(workspaceService.getAllWorkspacesByViewerId(viewerId));
    }
}
