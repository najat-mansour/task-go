package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.authentication.EmailVerificationResponseDTO;
import com.taskgo.dtos.authentication.LoginRequestDTO;
import com.taskgo.dtos.authentication.LoginResponseDTO;
import com.taskgo.dtos.general.MessageResponseDTO;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLs.AUTHENTICATION_PREFIX)
@RequiredArgsConstructor
@Tag(name = "1. Authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(URLs.AUTHENTICATION_LOGIN)
    public ResponseEntity<LoginResponseDTO> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(loginRequestDTO));
    }

    @GetMapping(URLs.AUTHENTICATION_EMAIL_VERIFICATION)
    public ResponseEntity<EmailVerificationResponseDTO> verifyEmail(@PathVariable String email)
            throws MessagingException, NoUsersFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.verifyEmail(email));
    }

    @GetMapping(URLs.AUTHENTICATION_FORGOT_PASSWORD)
    public ResponseEntity<MessageResponseDTO> forgotPassword(@PathVariable String username)
            throws MessagingException, NoUsersFoundException {
        authenticationService.forgotPassword(username);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageResponseDTO("A new password is sent to your email address!"));
    }
}
