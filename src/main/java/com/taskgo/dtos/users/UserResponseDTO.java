package com.taskgo.dtos.users;

import com.taskgo.constants.Gender;
import com.taskgo.dtos.addresses.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;
    private Gender gender;
    private AddressDTO address;
    private LocalDateTime createdAt;
    private Integer appRate;
}