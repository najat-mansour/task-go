package com.taskgo.dtos.workspaces;

import com.taskgo.dtos.groups.GroupResponseDTO;
import com.taskgo.dtos.users.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkspaceResponseDTO implements Serializable {
    private String id;
    private String name;
    private String description;
    private List<UserResponseDTO> viewers;
    private List<GroupResponseDTO> groups;
}
