package com.taskgo.controllers;

import com.taskgo.dtos.general.ErrorResponseDTO;
import com.taskgo.exceptions.*;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleNoResourceFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Resource Found!"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Invalid Parameters Type!"));
    }

    @ExceptionHandler(MessagingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleMessagingException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Messaging Exception is occurred!"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationErrorsMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NoUsersFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleNoUsersFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Users Found!"));
    }

    @ExceptionHandler(NoWorkspacesFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleNoWorkspacesFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Workspaces Found!"));
    }

    @ExceptionHandler(NoWorkspaceViewerMatchingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleNoWorkspaceViewerMatchingException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("No Workspace User Matching!"));
    }

    @ExceptionHandler(NoGroupsFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleNoGroupsFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Groups Found!"));
    }

    @ExceptionHandler(NoTasksFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleNoTasksFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Tasks Found!"));
    }

    @ExceptionHandler(NoSubTasksFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleNoSubTasksFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("No Sub-Tasks Found!"));
    }
}
