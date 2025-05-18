package com.taskgo.dtos.groups;

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
public class GroupUpdateRequestDTO {
    private String name;
    private String color;
    private String description;
}