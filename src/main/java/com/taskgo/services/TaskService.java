package com.taskgo.services;

import com.taskgo.dtos.tasks.TaskCreateRequestDTO;
import com.taskgo.dtos.tasks.TaskUpdateRequestDTO;
import com.taskgo.entities.Task;
import com.taskgo.exceptions.NoGroupsFoundException;
import com.taskgo.exceptions.NoTasksFoundException;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.mappers.TaskMapper;
import com.taskgo.repositories.GroupRepository;
import com.taskgo.repositories.TaskRepository;
import com.taskgo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class TaskService {
    private final TaskRepository taskRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public void createTask(String groupId, TaskCreateRequestDTO taskCreateRequestDTO) throws NoGroupsFoundException {
        log.info("Creating task: {} in group with ID: {}", taskCreateRequestDTO, groupId);
        Task task = taskMapper.toEntity(taskCreateRequestDTO);
        task.setGroup(groupRepository.findById(groupId).orElseThrow(NoGroupsFoundException::new));
        task.setAssignedTo(userRepository.findById(taskCreateRequestDTO.getAssignedToId()).orElseThrow(NoGroupsFoundException::new));
        taskRepository.save(task);
        log.info("Task created successfully!");
    }

    public void updateTask(String taskId, TaskUpdateRequestDTO taskUpdateRequestDTO) throws NoTasksFoundException, NoUsersFoundException {
        log.info("Updating task {}: {}", taskId, taskUpdateRequestDTO);
        Task task = taskRepository.findById(taskId).orElseThrow(NoTasksFoundException::new);
        if (taskUpdateRequestDTO.getName() != null) {
            task.setName(taskUpdateRequestDTO.getName());
        }
        if (taskUpdateRequestDTO.getDescription() != null) {
            task.setDescription(taskUpdateRequestDTO.getDescription());
        }
        if (taskUpdateRequestDTO.getStatus() != null) {
            task.setStatus(taskUpdateRequestDTO.getStatus());
        }
        if (taskUpdateRequestDTO.getPriority() != null) {
            task.setPriority(taskUpdateRequestDTO.getPriority());
        }
        if (taskUpdateRequestDTO.getStartingTimestamp() != null) {
            task.setStartingTimestamp(taskUpdateRequestDTO.getStartingTimestamp());
        }
        if (taskUpdateRequestDTO.getEndingTimestamp() != null) {
            task.setEndingTimestamp(taskUpdateRequestDTO.getEndingTimestamp());
        }
        if (taskUpdateRequestDTO.getIsFavorite() != null) {
            task.setIsFavorite(taskUpdateRequestDTO.getIsFavorite());
        }
        if (taskUpdateRequestDTO.getAssignedToId() != null) {
            task.setAssignedTo(userRepository.findById(taskUpdateRequestDTO.getAssignedToId()).orElseThrow(NoUsersFoundException::new));
        }
        taskRepository.save(task);
        log.info("Task updated successfully!");
    }

    public void deleteTask(String taskId) throws NoTasksFoundException {
        log.info("Deleting task {}", taskId);
        if (!taskRepository.existsById(taskId)) {
            log.warn("Task with ID {} is not found!", taskId);
            throw new NoTasksFoundException();
        }
        taskRepository.deleteById(taskId);
        log.info("Task deleted successfully!");
    }
}