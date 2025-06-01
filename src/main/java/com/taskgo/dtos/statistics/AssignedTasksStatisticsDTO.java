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
public class AssignedTasksStatisticsDTO implements Serializable {
    private Integer notStartedCount;
    private Integer inProgressCount;
    private Integer pendingCount;
    private Integer finishedCount;
    private Integer totalCount;
}
