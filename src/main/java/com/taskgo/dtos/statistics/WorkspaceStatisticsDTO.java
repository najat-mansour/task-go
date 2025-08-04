package com.taskgo.dtos.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkspaceStatisticsDTO implements Serializable {
    private Integer ownedWorkspacesCount;
    private Integer viewedWorkspacesCount;
}
