package com.taskgo.dtos.addresses;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressDTO {
    @NotBlank
    private String country;

    @NotBlank
    private String city;

    private String town;

    @NotBlank
    private String street;
}
