package com.taskgo.services;

import com.taskgo.dtos.subtasks.SubTaskCreateRequestDTO;
import com.taskgo.dtos.subtasks.SubTaskResponseDTO;
import com.taskgo.dtos.subtasks.SubTaskUpdateRequestDTO;
import com.taskgo.entities.SubTask;
import com.taskgo.events.SubTasksChangedEvent;
import com.taskgo.exceptions.NoSubTasksFoundException;
import com.taskgo.exceptions.NoTasksFoundException;
import com.taskgo.mappers.SubTaskMapper;
import com.taskgo.repositories.SubTaskRepository;
import com.taskgo.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubTaskService {
    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;
    private final SubTaskMapper subTaskMapper;

    private final ApplicationEventPublisher eventPublisher;

    private void publishSubTasksChangedEvent(SubTask subTask) {
        eventPublisher.publishEvent(new SubTasksChangedEvent(
                subTask.getId(),
                subTask.getTask().getId(),
                subTask.getTask().getGroup().getId(),
                subTask.getTask().getGroup().getWorkspace().getId()
        ));
    }

    public void createSubTask(String taskId, SubTaskCreateRequestDTO subTaskCreateRequestDTO) throws NoTasksFoundException {
        SubTask subTask = subTaskMapper.toEntity(subTaskCreateRequestDTO);
        subTask.setTask(taskRepository.findById(taskId).orElseThrow(NoTasksFoundException::new));
        subTaskRepository.save(subTask);

        publishSubTasksChangedEvent(subTask);
    }

    public void updateSubTask(String subTaskId, SubTaskUpdateRequestDTO subTaskUpdateRequestDTO) throws NoSubTasksFoundException {
        SubTask subTask = subTaskRepository.findById(subTaskId).orElseThrow(NoSubTasksFoundException::new);
        if (subTaskUpdateRequestDTO.getName() != null) {
            subTask.setName(subTaskUpdateRequestDTO.getName());
        }
        if (subTaskUpdateRequestDTO.getDescription() != null) {
            subTask.setDescription(subTaskUpdateRequestDTO.getDescription());
        }
        if (subTaskUpdateRequestDTO.getStatus() != null) {
            subTask.setStatus(subTaskUpdateRequestDTO.getStatus());
        }
        if (subTaskUpdateRequestDTO.getPriority() != null) {
            subTask.setPriority(subTaskUpdateRequestDTO.getPriority());
        }
        if (subTaskUpdateRequestDTO.getStartingTimestamp() != null) {
            subTask.setStartingTimestamp(subTaskUpdateRequestDTO.getStartingTimestamp());
        }
        if (subTaskUpdateRequestDTO.getEndingTimestamp() != null) {
            subTask.setEndingTimestamp(subTaskUpdateRequestDTO.getEndingTimestamp());
        }
        subTaskRepository.save(subTask);

        publishSubTasksChangedEvent(subTask);
    }

    public void deleteSubTask(String subTaskId) throws NoSubTasksFoundException {
        SubTask subTask = subTaskRepository.findById(subTaskId).orElseThrow(NoSubTasksFoundException::new);
        subTaskRepository.delete(subTask);

        publishSubTasksChangedEvent(subTask);
    }

    @Cacheable(value = "subtasks:by-id", key = "#subTaskId")
    public SubTaskResponseDTO getSubTaskById(String subTaskId) throws NoSubTasksFoundException {
        Optional<SubTask> subTask = subTaskRepository.findById(subTaskId);
        return subTask.map(subTaskMapper::toResponseDTO).orElseThrow(NoSubTasksFoundException::new);
    }
}
