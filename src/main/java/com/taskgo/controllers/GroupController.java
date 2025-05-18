package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.groups.GroupCreateRequestDTO;
import com.taskgo.dtos.groups.GroupUpdateRequestDTO;
import com.taskgo.exceptions.NoGroupsFoundException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.services.GroupService;
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
public class GroupController {
    private final GroupService groupService;

    @PostMapping(URLs.GROUPS_CREATE)
    public ResponseEntity<MessageResponseDTO> createGroup (@PathVariable String workspaceId, @RequestBody @Valid GroupCreateRequestDTO groupCreateRequestDTO) throws NoWorkspacesFoundException {
        log.info("Creating group: {} in Workspace ID: {}", groupCreateRequestDTO, workspaceId);
        groupService.createGroup(workspaceId, groupCreateRequestDTO);
        log.info("Group created successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Group created successfully!"));
    }

    @PatchMapping(URLs.GROUPS_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateGroup (@PathVariable String groupId, @RequestBody @Valid GroupUpdateRequestDTO groupUpdateRequestDTO) throws NoGroupsFoundException {
        log.info("Updating group {}: {} ", groupId, groupUpdateRequestDTO);
        groupService.updateGroup(groupId, groupUpdateRequestDTO);
        log.info("Group updated successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Group updated successfully!"));
    }

    @DeleteMapping(URLs.GROUPS_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteGroup (@PathVariable String groupId) throws NoGroupsFoundException {
        log.info("Deleting group {}", groupId);
        groupService.deleteGroup(groupId);
        log.info("Group deleted successfully!");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("Group deleted successfully!"));
    }
}