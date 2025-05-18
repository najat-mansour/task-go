package com.taskgo.dtos.groups;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GroupCreateRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Pattern(
            regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$",
            message = "Color must be a valid hex code like #fff or #123456"
    )
    private String color;

    @NotBlank
    private String description;
}