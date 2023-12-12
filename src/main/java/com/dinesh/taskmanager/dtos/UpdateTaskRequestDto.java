package com.dinesh.taskmanager.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskRequestDto {
    private String description;
    private String deadLine;
    private Boolean completed;
}
