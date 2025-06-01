package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.groups.GroupCreateRequestDTO;
import com.taskgo.dtos.groups.GroupResponseDTO;
import com.taskgo.dtos.groups.GroupUpdateRequestDTO;
import com.taskgo.exceptions.NoGroupsFoundException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.services.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLs.WORKSPACES_PREFIX)
@RequiredArgsConstructor
@Tag(name = "5. Groups")
public class GroupController {
    private final GroupService groupService;

    @PostMapping(URLs.GROUPS_CREATE)
    public ResponseEntity<MessageResponseDTO> createGroup (@PathVariable String workspaceId, @RequestBody @Valid GroupCreateRequestDTO groupCreateRequestDTO) throws NoWorkspacesFoundException {
        groupService.createGroup(workspaceId, groupCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Group created successfully!"));
    }

    @PatchMapping(URLs.GROUPS_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateGroup (@PathVariable String groupId, @RequestBody @Valid GroupUpdateRequestDTO groupUpdateRequestDTO) throws NoGroupsFoundException {
        groupService.updateGroup(groupId, groupUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Group updated successfully!"));
    }

    @DeleteMapping(URLs.GROUPS_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteGroup (@PathVariable String groupId) throws NoGroupsFoundException {
        groupService.deleteGroup(groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("Group deleted successfully!"));
    }

    @GetMapping(URLs.GROUPS_GET_BY_ID)
    public ResponseEntity<GroupResponseDTO> getGroupById (@PathVariable String groupId) throws NoGroupsFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroupById(groupId));
    }
}
