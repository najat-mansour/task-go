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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    public void createUser(UserCreateRequestDTO userCreateRequestDTO) {
        User user = userMapper.toEntity(userCreateRequestDTO);
        String encryptedPassword = PasswordEncryptionUtil.encodePassword(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    public void updateUser(String id, UserUpdateRequestDTO userUpdateRequestDTO) throws NoUsersFoundException {
        User user = userRepository.findById(id).orElseThrow(NoUsersFoundException::new);
        if (userUpdateRequestDTO.getPassword() != null) {
            String encryptedPassword = PasswordEncryptionUtil.encodePassword(userUpdateRequestDTO.getPassword());
            user.setPassword(encryptedPassword);
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
            user.setAddress(addressMapper.toEntity(userUpdateRequestDTO.getAddress()));
        }
        userRepository.save(user);
    }

    public List<UserResponseDTO> getAllUsers() throws NoUsersFoundException {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoUsersFoundException();
        }
        return users.stream().map(userMapper::toDTO).toList();
    }

    public UserResponseDTO getUserById(String id) throws NoUsersFoundException {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDTO).orElseThrow(NoUsersFoundException::new);
    }

    public UserResponseDTO getUserByUsername(String username) throws NoUsersFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(userMapper::toDTO).orElseThrow(NoUsersFoundException::new);
    }

    public UserResponseDTO getUserByEmail(String email) throws NoUsersFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(userMapper::toDTO).orElseThrow(NoUsersFoundException::new);
    }

}