package com.dinesh.taskmanager.dtos;

import lombok.Data;

@Data
public class CreateTaskRequestDto {
    private String title;
    private String description;
    private String deadLine;
}
