package com.taskgo.services;

import com.taskgo.dtos.users.UserCreateRequestDTO;
import com.taskgo.dtos.users.UserResponseDTO;
import com.taskgo.dtos.users.UserUpdateRequestDTO;
import com.taskgo.entities.User;
import com.taskgo.events.UsersChangedEvent;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.mappers.UserMapper;
import com.taskgo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher eventPublisher;

    private void publishUsersChangedEvent(User user) {
        eventPublisher.publishEvent(new UsersChangedEvent(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        ));
    }

    public void createUser(UserCreateRequestDTO userCreateRequestDTO) {
        User user = userMapper.toEntity(userCreateRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getAddress().setUser(user);
        userRepository.save(user);

        publishUsersChangedEvent(user);
    }

    public void updateUser(String id, UserUpdateRequestDTO userUpdateRequestDTO) throws NoUsersFoundException {
        User user = userRepository.findById(id).orElseThrow(NoUsersFoundException::new);
        if (userUpdateRequestDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateRequestDTO.getPassword()));
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

        publishUsersChangedEvent(user);
    }

    @Cacheable("users:all")
    public List<UserResponseDTO> getAllUsers() throws NoUsersFoundException {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoUsersFoundException();
        }
        return users.stream().map(userMapper::toResponseDTO).toList();
    }

    @Cacheable(value = "users:by-id", key = "#id")
    public UserResponseDTO getUserById(String id) throws NoUsersFoundException {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toResponseDTO).orElseThrow(NoUsersFoundException::new);
    }

    @Cacheable(value = "users:by-username", key = "#username")
    public UserResponseDTO getUserByUsername(String username) throws NoUsersFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(userMapper::toResponseDTO).orElseThrow(NoUsersFoundException::new);
    }

    @Cacheable(value = "users:by-email", key = "#email")
    public UserResponseDTO getUserByEmail(String email) throws NoUsersFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(userMapper::toResponseDTO).orElseThrow(NoUsersFoundException::new);
    }
}
