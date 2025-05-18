package com.taskgo.controllers;

import com.taskgo.dtos.general.ErrorResponseDTO;
import com.taskgo.exceptions.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(NoResourceFoundException ex) {
        log.error("No Resource Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Resource Found!"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handle(MethodArgumentTypeMismatchException ex) {
        log.error("Method Argument Type Mismatch Exception");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Invalid Parameters Type!"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        log.error(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NoUsersFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(NoUsersFoundException ex) {
        log.error("No Users Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Users Found!"));
    }

    @ExceptionHandler(NoWorkspacesFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(NoWorkspacesFoundException ex) {
        log.error("No Workspaces Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Workspaces Found!"));
    }

    @ExceptionHandler(NoWorkspaceViewerMatchingException.class)
    public ResponseEntity<ErrorResponseDTO> handle(NoWorkspaceViewerMatchingException ex) {
        log.error("No Workspace User Matching Exception");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("No Workspace User Matching!"));
    }

    @ExceptionHandler(NoGroupsFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(NoGroupsFoundException ex) {
        log.error("No Groups Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Groups Found!"));
    }

    @ExceptionHandler(NoTasksFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(NoTasksFoundException ex) {
        log.error("No Tasks Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Tasks Found!"));
    }

    @ExceptionHandler(NoSubTasksFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(NoSubTasksFoundException ex) {
        log.error("No Sub-Tasks Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Sub-Tasks Found!"));
    }
}