package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.tasks.TaskCreateRequestDTO;
import com.taskgo.dtos.tasks.TaskUpdateRequestDTO;
import com.taskgo.exceptions.NoGroupsFoundException;
import com.taskgo.exceptions.NoTasksFoundException;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLs.WORKSPACES_PREFIX)
@RequiredArgsConstructor
@Log4j2
public class TaskController {
    private final TaskService taskService;

    @PostMapping(URLs.TASKS_CREATE)
    public ResponseEntity<MessageResponseDTO> createTask(@PathVariable String groupId, @RequestBody @Valid TaskCreateRequestDTO taskCreateRequestDTO) throws NoGroupsFoundException {
        log.info("Creating task: {} in group with ID: {}", taskCreateRequestDTO, groupId);
        taskService.createTask(groupId, taskCreateRequestDTO);
        log.info("Task created successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Task created successfully!"));
    }

    @PatchMapping(URLs.TASKS_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateTask(@PathVariable String taskId, @RequestBody @Valid TaskUpdateRequestDTO taskUpdateRequestDTO) throws NoGroupsFoundException, NoTasksFoundException, NoUsersFoundException {
        log.info("Updating task {}: {}", taskId, taskUpdateRequestDTO);
        taskService.updateTask(taskId, taskUpdateRequestDTO);
        log.info("Task updated successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Task updated successfully!"));
    }

    @DeleteMapping(URLs.TASKS_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteTask(@PathVariable String taskId) throws NoTasksFoundException {
        log.info("Deleting task {}", taskId);
        taskService.deleteTask(taskId);
        log.info("Task deleted successfully!");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("Task deleted successfully!"));
    }
}