package com.taskgo.dtos.addresses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressDTO implements Serializable {
    @NotBlank
    private String country;

    @NotBlank
    private String city;

    // Can be blank
    private String town;

    @NotBlank
    private String street;
}
