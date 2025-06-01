package com.taskgo.services;

import com.taskgo.constants.Status;
import com.taskgo.dtos.statistics.AssignedTasksStatisticsDTO;
import com.taskgo.dtos.statistics.UserStatisticsResponseDTO;
import com.taskgo.dtos.statistics.WorkspaceStatisticsDTO;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.repositories.TaskRepository;
import com.taskgo.repositories.UserRepository;
import com.taskgo.repositories.WorkspaceRepository;
import com.taskgo.repositories.WorkspaceViewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceViewerRepository workspaceViewerRepository;
    private final TaskRepository taskRepository;

    @Cacheable(value = "statistics:user", key = "#userId")
    public UserStatisticsResponseDTO getUserStatistics(String userId) throws NoUsersFoundException {
        if (!userRepository.existsById(userId)) {
            throw new NoUsersFoundException();
        }

        // Generate Workspace Statistics
        Integer ownedWorkspacesCount = workspaceRepository.countAllByOwnerId(userId);
        Integer viewedWorkspacesCount = workspaceViewerRepository.countAllByViewerId(userId);
        WorkspaceStatisticsDTO workspaceStatisticsDTO = new WorkspaceStatisticsDTO(
                ownedWorkspacesCount,
                viewedWorkspacesCount
        );

        // Generate Assigned Tasks Statistics
        Integer assignedTasksNotStartedCount = taskRepository.countAllByAssignedToIdAndStatus(userId, Status.NOT_STARTED);
        Integer assignedTasksInProgressCount = taskRepository.countAllByAssignedToIdAndStatus(userId, Status.IN_PROGRESS);
        Integer assignedTasksPendingCount = taskRepository.countAllByAssignedToIdAndStatus(userId, Status.PENDING);
        Integer assignedTasksFinishedCount = taskRepository.countAllByAssignedToIdAndStatus(userId, Status.FINISHED);
        Integer assignedTasksNotTotalCount = taskRepository.countAllByAssignedToId(userId);
        AssignedTasksStatisticsDTO assignedTasksStatisticsDTO = new AssignedTasksStatisticsDTO(
                assignedTasksNotStartedCount,
                assignedTasksInProgressCount,
                assignedTasksPendingCount,
                assignedTasksFinishedCount,
                assignedTasksNotTotalCount
        );

        // Generate User Statistics Response from Both Workspace and Assigned Tasks Statistics
        return new UserStatisticsResponseDTO(
                workspaceStatisticsDTO,
                assignedTasksStatisticsDTO
        );
    }
}
