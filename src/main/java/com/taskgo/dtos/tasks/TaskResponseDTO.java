package com.taskgo.dtos.tasks;

import com.taskgo.constants.Priority;
import com.taskgo.constants.Status;
import com.taskgo.dtos.subtasks.SubTaskResponseDTO;
import com.taskgo.dtos.users.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskResponseDTO implements Serializable {
    private String id;
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private Boolean isFavorite;
    private LocalDateTime startingTimestamp;
    private LocalDateTime endingTimestamp;
    private UserResponseDTO assignedTo;
    private List<SubTaskResponseDTO> subTasks;
}
