package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.subtasks.SubTaskCreateRequestDTO;
import com.taskgo.dtos.subtasks.SubTaskResponseDTO;
import com.taskgo.dtos.subtasks.SubTaskUpdateRequestDTO;
import com.taskgo.exceptions.NoSubTasksFoundException;
import com.taskgo.exceptions.NoTasksFoundException;
import com.taskgo.services.SubTaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLs.WORKSPACES_PREFIX)
@RequiredArgsConstructor
@Tag(name = "7. Sub-Tasks")
public class SubTaskController {
    private final SubTaskService subTaskService;

    @PostMapping(URLs.SUB_TASKS_CREATE)
    public ResponseEntity<MessageResponseDTO> createSubTask(@PathVariable String taskId, @RequestBody @Valid SubTaskCreateRequestDTO subTaskCreateRequestDTO) throws NoTasksFoundException {
        subTaskService.createSubTask(taskId, subTaskCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Sub-task created successfully!"));
    }

    @PatchMapping(URLs.SUB_TASKS_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateSubTask(@PathVariable String subTaskId, @RequestBody @Valid SubTaskUpdateRequestDTO subTaskUpdateRequestDTO) throws NoSubTasksFoundException {
        subTaskService.updateSubTask(subTaskId, subTaskUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Sub-task updated successfully!"));
    }

    @DeleteMapping(URLs.SUB_TASKS_DELETE)
    public ResponseEntity<MessageResponseDTO> deleteSubTask(@PathVariable String subTaskId) throws NoSubTasksFoundException {
        subTaskService.deleteSubTask(subTaskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponseDTO("Sub-task deleted successfully!"));
    }

    @GetMapping(URLs.SUB_TASKS_GET_BY_ID)
    public ResponseEntity<SubTaskResponseDTO> getSubTaskById(@PathVariable String subTaskId) throws NoSubTasksFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(subTaskService.getSubTaskById(subTaskId));
    }
}
