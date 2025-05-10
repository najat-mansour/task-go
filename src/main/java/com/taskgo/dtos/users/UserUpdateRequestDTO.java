package com.taskgo.dtos.users;

import com.taskgo.constants.Gender;
import com.taskgo.dtos.addresses.AddressDTO;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserUpdateRequestDTO {
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a digit, and a special character."
    )
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Gender gender;
    private AddressDTO address;
    private Integer appRate;
}
