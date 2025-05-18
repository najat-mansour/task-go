package com.taskgo.services;

import com.taskgo.dtos.subtasks.SubTaskCreateRequestDTO;
import com.taskgo.dtos.subtasks.SubTaskUpdateRequestDTO;
import com.taskgo.entities.SubTask;
import com.taskgo.exceptions.NoSubTasksFoundException;
import com.taskgo.exceptions.NoTasksFoundException;
import com.taskgo.mappers.SubTaskMapper;
import com.taskgo.repositories.SubTaskRepository;
import com.taskgo.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SubTaskService {
    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;
    private final SubTaskMapper subTaskMapper;

    public void createSubTask(String taskId, SubTaskCreateRequestDTO subTaskCreateRequestDTO) throws NoTasksFoundException {
        log.info("Creating subtask for task with ID: {}", taskId);
        SubTask subTask = subTaskMapper.toEntity(subTaskCreateRequestDTO);
        subTask.setTask(taskRepository.findById(taskId).orElseThrow(NoTasksFoundException::new));
        subTaskRepository.save(subTask);
        log.info("Subtask created successfully!");
    }

    public void updateSubTask(String subTaskId, SubTaskUpdateRequestDTO subTaskUpdateRequestDTO) throws NoSubTasksFoundException {
        log.info("Updating subtask {}: {}", subTaskId, subTaskUpdateRequestDTO);
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
        log.info("Subtask updated successfully!");
    }

    public void deleteSubTask(String subTaskId) throws NoSubTasksFoundException {
        log.info("Deleting subtask with ID: {}", subTaskId);
        if (!subTaskRepository.existsById(subTaskId)) {
            log.error("Subtask with ID {} is not found!", subTaskId);
            throw new NoSubTasksFoundException();
        }
        subTaskRepository.deleteById(subTaskId);
        log.info("Subtask deleted successfully!");
    }
}