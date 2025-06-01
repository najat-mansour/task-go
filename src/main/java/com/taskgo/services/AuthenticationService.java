package com.taskgo.services;

import com.taskgo.dtos.authentication.EmailVerificationResponseDTO;
import com.taskgo.dtos.authentication.LoginRequestDTO;
import com.taskgo.dtos.authentication.LoginResponseDTO;
import com.taskgo.entities.User;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.repositories.UserRepository;
import com.taskgo.security.JwtUtil;
import com.taskgo.utilities.RandomsGeneratorUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtils;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails.getUsername());
        return new LoginResponseDTO(token);
    }

    public EmailVerificationResponseDTO verifyEmail(String email) throws MessagingException, NoUsersFoundException {
        if (!userRepository.existsByEmail(email)) {
            throw new NoUsersFoundException();
        }
        String verificationCode = RandomsGeneratorUtil.generateFourDigitCode();
        emailSenderService.sendEmailVerificationEmail(email);
        return new EmailVerificationResponseDTO(verificationCode);
    }

    public void forgotPassword(String username) throws NoUsersFoundException, MessagingException {
        User user = userRepository.findByUsername(username).orElseThrow(NoUsersFoundException::new);
        emailSenderService.sendNewPasswordEmail(user.getEmail());
    }
}
