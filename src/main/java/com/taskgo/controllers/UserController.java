package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.users.UserCreateRequestDTO;
import com.taskgo.dtos.users.UserResponseDTO;
import com.taskgo.dtos.users.UserUpdateRequestDTO;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLs.USERS_PREFIX)
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserService userService;

    @PostMapping(URLs.USERS_CREATE)
    public ResponseEntity<MessageResponseDTO> createUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        log.info("Creating user: {}", userCreateRequestDTO);
        userService.createUser(userCreateRequestDTO);
        log.info("User created successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("User Created Successfully!"));
    }

    @PatchMapping(URLs.USERS_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO) throws NoUsersFoundException {
        log.info("Updating user {}: {}", id, userUpdateRequestDTO);
        userService.updateUser(id, userUpdateRequestDTO);
        log.info("User updated successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("User Updated Successfully!"));
    }

    @GetMapping(URLs.USERS_GET_ALL)
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() throws NoUsersFoundException {
        log.info("Getting all users");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping(URLs.USERS_GET_BY_ID)
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) throws NoUsersFoundException {
        log.info("Getting user by id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @GetMapping(URLs.USERS_GET_BY_USERNAME)
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) throws NoUsersFoundException {
        log.info("Getting user by username: {}", username);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUsername(username));
    }

    @GetMapping(URLs.USERS_GET_BY_EMAIL)
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) throws NoUsersFoundException {
        log.info("Getting user by user email: {}", email);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
    }
}