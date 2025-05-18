package com.taskgo.dtos.workspacesviewers;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkspaceViewerCreateRequestDTO {
    @NotBlank
    private String viewerId;
}