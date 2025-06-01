package com.taskgo.dtos.groups;

import com.taskgo.dtos.tasks.TaskResponseDTO;
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
public class GroupResponseDTO implements Serializable {
    private String id;
    private String name;
    private String description;
    private List<TaskResponseDTO> tasks;
}
