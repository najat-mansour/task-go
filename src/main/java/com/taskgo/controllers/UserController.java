package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.dtos.users.UserCreateRequestDTO;
import com.taskgo.dtos.users.UserResponseDTO;
import com.taskgo.dtos.users.UserUpdateRequestDTO;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLs.USERS_PREFIX)
@RequiredArgsConstructor
@Tag(name = "2. Users")
public class UserController {
    private final UserService userService;

    @PostMapping(URLs.USERS_CREATE)
    public ResponseEntity<MessageResponseDTO> createUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        userService.createUser(userCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("User Created Successfully!"));
    }

    @PatchMapping(URLs.USERS_UPDATE)
    public ResponseEntity<MessageResponseDTO> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO) throws NoUsersFoundException {
        userService.updateUser(id, userUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("User Updated Successfully!"));
    }


    @GetMapping(URLs.USERS_GET_ALL)
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() throws NoUsersFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping(URLs.USERS_GET_BY_ID)
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) throws NoUsersFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @GetMapping(URLs.USERS_GET_BY_USERNAME)
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) throws NoUsersFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUsername(username));
    }

    @GetMapping(URLs.USERS_GET_BY_EMAIL)
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) throws NoUsersFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
    }
}
