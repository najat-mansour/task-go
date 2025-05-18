package com.taskgo.dtos.subtasks;

import com.taskgo.constants.Priority;
import com.taskgo.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubTaskUpdateRequestDTO {
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime startingTimestamp;
    private LocalDateTime endingTimestamp;
}