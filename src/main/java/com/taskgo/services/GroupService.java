package com.taskgo.services;

import com.taskgo.dtos.groups.GroupCreateRequestDTO;
import com.taskgo.dtos.groups.GroupResponseDTO;
import com.taskgo.dtos.groups.GroupUpdateRequestDTO;
import com.taskgo.entities.Group;
import com.taskgo.events.GroupsChangedEvent;
import com.taskgo.exceptions.NoGroupsFoundException;
import com.taskgo.exceptions.NoWorkspacesFoundException;
import com.taskgo.mappers.GroupMapper;
import com.taskgo.repositories.GroupRepository;
import com.taskgo.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final WorkspaceRepository workspaceRepository;
    private final GroupMapper groupMapper;

    private final ApplicationEventPublisher eventPublisher;

    private void publishGroupCreatedEvent(Group group) {
        eventPublisher.publishEvent(new GroupsChangedEvent(
           group.getId(),
           group.getWorkspace().getId()
        ));
    }

    public void createGroup(String workspaceId, GroupCreateRequestDTO groupCreateRequestDTO) throws NoWorkspacesFoundException {
        Group group = groupMapper.toEntity(groupCreateRequestDTO);
        group.setWorkspace(workspaceRepository.findById(workspaceId).orElseThrow(NoWorkspacesFoundException::new));
        groupRepository.save(group);

        publishGroupCreatedEvent(group);
    }

    public void updateGroup(String groupId, GroupUpdateRequestDTO groupUpdateRequestDTO) throws NoGroupsFoundException {
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

        publishGroupCreatedEvent(group);
    }

    public void deleteGroup(String groupId) throws NoGroupsFoundException {
        Group group = groupRepository.findById(groupId).orElseThrow(NoGroupsFoundException::new);
        groupRepository.delete(group);

        publishGroupCreatedEvent(group);
    }

    @Cacheable(value = "groups:by-id", key = "#groupId")
    public GroupResponseDTO getGroupById(String groupId) throws NoGroupsFoundException {
        Optional<Group> group = groupRepository.findById(groupId);
        return group.map(groupMapper::toResponseDTO).orElseThrow(NoGroupsFoundException::new);
    }
}
