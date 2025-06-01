package com.taskgo.dtos.users;

import com.taskgo.constants.Gender;
import com.taskgo.dtos.addresses.AddressDTO;
import com.taskgo.dtos.workspaces.WorkspaceResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDTO implements Serializable {
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
