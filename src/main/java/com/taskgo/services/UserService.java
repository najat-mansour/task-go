package com.taskgo.services;

import com.taskgo.dtos.users.UserCreateRequestDTO;
import com.taskgo.dtos.users.UserResponseDTO;
import com.taskgo.dtos.users.UserUpdateRequestDTO;
import com.taskgo.entities.User;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.mappers.AddressMapper;
import com.taskgo.mappers.UserMapper;
import com.taskgo.repositories.UserRepository;
import com.taskgo.utilities.PasswordEncryptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public void createUser(UserCreateRequestDTO userCreateRequestDTO) {
        log.info("Creating user: {}", userCreateRequestDTO);
        User user = userMapper.toEntity(userCreateRequestDTO);
        user.setPassword(PasswordEncryptionUtil.encodePassword(user.getPassword()));
        user.getAddress().setUser(user);
        userRepository.save(user);
        log.info("User created successfully!");
    }

    public void updateUser(String id, UserUpdateRequestDTO userUpdateRequestDTO) throws NoUsersFoundException {
        log.info("Updating user {}: {}", id, userUpdateRequestDTO);
        User user = userRepository.findById(id).orElseThrow(NoUsersFoundException::new);
        if (userUpdateRequestDTO.getPassword() != null) {
            user.setPassword(PasswordEncryptionUtil.encodePassword(userUpdateRequestDTO.getPassword()));
        }
        if (userUpdateRequestDTO.getFirstName() != null) {
            user.setFirstName(userUpdateRequestDTO.getFirstName());
        }
        if (userUpdateRequestDTO.getLastName() != null) {
            user.setLastName(userUpdateRequestDTO.getLastName());
        }
        if (userUpdateRequestDTO.getBirthdate() != null) {
            user.setBirthdate(userUpdateRequestDTO.getBirthdate());
        }
        if (userUpdateRequestDTO.getGender() != null) {
            user.setGender(userUpdateRequestDTO.getGender());
        }
        if (userUpdateRequestDTO.getAddress() != null) {
            if (userUpdateRequestDTO.getAddress().getCountry() != null) {
                user.getAddress().setCountry(userUpdateRequestDTO.getAddress().getCountry());
            }
            if (userUpdateRequestDTO.getAddress().getCity() != null) {
                user.getAddress().setCity(userUpdateRequestDTO.getAddress().getCity());
            }
            if (userUpdateRequestDTO.getAddress().getTown() != null) {
                user.getAddress().setTown(userUpdateRequestDTO.getAddress().getTown());
            }
            if (userUpdateRequestDTO.getAddress().getStreet() != null) {
                user.getAddress().setStreet(userUpdateRequestDTO.getAddress().getStreet());
            }
        }
        userRepository.save(user);
        log.info("User updated successfully!");
    }

    public List<UserResponseDTO> getAllUsers() throws NoUsersFoundException {
        log.info("Getting all users");
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.error("No users found");
            throw new NoUsersFoundException();
        }
        List<UserResponseDTO> userResponseDTOS = users.stream().map(userMapper::toDTO).toList();
        log.info("Users found: {}", userResponseDTOS);
        return userResponseDTOS;
    }

    public UserResponseDTO getUserById(String id) throws NoUsersFoundException {
        log.info("Getting user by id: {}", id);
        Optional<User> user = userRepository.findById(id);
        UserResponseDTO userResponseDTO = user.map(userMapper::toDTO).orElseThrow(NoUsersFoundException::new);
        log.info("User found by id: {}", userResponseDTO);
        return userResponseDTO;
    }

    public UserResponseDTO getUserByUsername(String username) throws NoUsersFoundException {
        log.info("Getting user by username: {}", username);
        Optional<User> user = userRepository.findByUsername(username);
        UserResponseDTO userResponseDTO = user.map(userMapper::toDTO).orElseThrow(NoUsersFoundException::new);
        log.info("User found by username: {}", userResponseDTO);
        return userResponseDTO;
    }

    public UserResponseDTO getUserByEmail(String email) throws NoUsersFoundException {
        log.info("Getting user by email: {}", email);
        Optional<User> user = userRepository.findByEmail(email);
        UserResponseDTO userResponseDTO = user.map(userMapper::toDTO).orElseThrow(NoUsersFoundException::new);
        log.info("User found by email: {}", userResponseDTO);
        return userResponseDTO;
    }
}