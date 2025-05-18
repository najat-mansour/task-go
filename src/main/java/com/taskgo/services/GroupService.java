package com.taskgo.services;

import com.taskgo.dtos.groups.GroupCreateRequestDTO;
import com.taskgo.dtos.groups.GroupUpdateRequestDTO;
import com.taskgo.entities.Group;
import com.taskgo.exceptions.NoGroupsFoundException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.mappers.GroupMapper;
import com.taskgo.repositories.GroupRepository;
import com.taskgo.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class GroupService {
    private final GroupRepository groupRepository;
    private final WorkspaceRepository workspaceRepository;
    private final GroupMapper groupMapper;

    public void createGroup(String workspaceId, GroupCreateRequestDTO groupCreateRequestDTO) throws NoWorkspacesFoundException {
        log.info("Creating group: {} in Workspace ID: {}", groupCreateRequestDTO, workspaceId);
        Group group = groupMapper.toEntity(groupCreateRequestDTO);
        group.setWorkspace(workspaceRepository.findById(workspaceId).orElseThrow(NoWorkspacesFoundException::new));
        groupRepository.save(group);
        log.info("Group created successfully!");
    }

    public void updateGroup(String groupId, GroupUpdateRequestDTO groupUpdateRequestDTO) throws NoGroupsFoundException {
        log.info("Updating group {}: {}", groupId, groupUpdateRequestDTO);
        Group group = groupRepository.findById(groupId).orElseThrow(NoGroupsFoundException::new);
        if (groupUpdateRequestDTO.getName() != null) {
            group.setName(groupUpdateRequestDTO.getName());
        }
        if (groupUpdateRequestDTO.getColor() != null) {
            group.setColor(groupUpdateRequestDTO.getColor());
        }
        if (groupUpdateRequestDTO.getDescription() != null) {
            group.setDescription(groupUpdateRequestDTO.getDescription());
        }
        groupRepository.save(group);
        log.info("Group updated successfully!");
    }

    public void deleteGroup(String groupId) throws NoGroupsFoundException {
        log.info("Deleting group {}", groupId);
        if (!groupRepository.existsById(groupId)) {
            log.error("Group {} is not found!", groupId);
            throw new NoGroupsFoundException();
        }
        groupRepository.deleteById(groupId);
        log.info("Group deleted successfully!");
    }
}