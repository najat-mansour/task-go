package com.taskgo.dtos.tasks;

import com.taskgo.constants.Priority;
import com.taskgo.constants.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TaskCreateRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Status status;

    @NotNull
    private Priority priority;

    @NotNull
    private LocalDateTime startingTimestamp;

    @NotNull
    private LocalDateTime endingTimestamp;

    @NotNull
    private Boolean isFavorite;

    @NotBlank
    private String assignedToId;
}