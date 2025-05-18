package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.subtasks.SubTaskCreateRequestDTO;
import com.taskgo.dtos.subtasks.SubTaskUpdateRequestDTO;
import com.taskgo.exceptions.NoSubTasksFoundException;
import com.taskgo.exceptions.NoTasksFoundException;
import com.taskgo.services.SubTaskService;
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
public class SubTaskController {
    private final SubTaskService subTaskService;

    @PostMapping(URLs.SUB_TASKS_CREATE)
    public ResponseEntity<MessageResponseDTO> createSubTask(@PathVariable String taskId, @RequestBody @Valid SubTaskCreateRequestDTO subTaskCreateRequestDTO) throws NoTasksFoundException {
        log.info("Creating sub-task: {} of task with ID: {}", subTaskCreateRequestDTO, taskId);
        subTaskService.createSubTask(taskId, subTaskCreateRequestDTO);
        log.info("SubTask created successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("SubTask created successfully!"));
    }

    @PatchMapping(URLs.SUB_TASKS_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateSubTask(@PathVariable String subTaskId, @RequestBody @Valid SubTaskUpdateRequestDTO subTaskUpdateRequestDTO) throws NoSubTasksFoundException {
        log.info("Updating sub-task {}: {}", subTaskId, subTaskUpdateRequestDTO);
        subTaskService.updateSubTask(subTaskId, subTaskUpdateRequestDTO);
        log.info("SubTask updated successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("SubTask updated successfully!"));
    }

    @DeleteMapping(URLs.SUB_TASKS_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteSubTask(@PathVariable String subTaskId) throws NoSubTasksFoundException {
        log.info("Deleting sub-task: {}", subTaskId);
        subTaskService.deleteSubTask(subTaskId);
        log.info("SubTask deleted successfully!");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("SubTask deleted successfully!"));
    }
}