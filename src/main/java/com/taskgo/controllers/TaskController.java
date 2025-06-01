package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.tasks.TaskCreateRequestDTO;
import com.taskgo.dtos.tasks.TaskResponseDTO;
import com.taskgo.dtos.tasks.TaskUpdateRequestDTO;
import com.taskgo.exceptions.NoGroupsFoundException;
import com.taskgo.exceptions.NoTasksFoundException;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.services.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLs.WORKSPACES_PREFIX)
@RequiredArgsConstructor
@Tag(name = "6. Tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping(URLs.TASKS_CREATE)
    public ResponseEntity<MessageResponseDTO> createTask(@PathVariable String groupId, @RequestBody @Valid TaskCreateRequestDTO taskCreateRequestDTO) throws NoGroupsFoundException, MessagingException, NoUsersFoundException {
        taskService.createTask(groupId, taskCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Task created successfully!"));
    }

    @PatchMapping(URLs.TASKS_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateTask(@PathVariable String taskId, @RequestBody @Valid TaskUpdateRequestDTO taskUpdateRequestDTO) throws NoTasksFoundException, NoUsersFoundException, MessagingException {
        taskService.updateTask(taskId, taskUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Task updated successfully!"));
    }

    @DeleteMapping(URLs.TASKS_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteTask(@PathVariable String taskId) throws NoTasksFoundException {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("Task deleted successfully!"));
    }

    @GetMapping(URLs.TASKS_GET_BY_ID)
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable String taskId) throws NoTasksFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(taskId));
    }
}
