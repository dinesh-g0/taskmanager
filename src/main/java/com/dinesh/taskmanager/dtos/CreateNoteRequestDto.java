package com.dinesh.taskmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateNoteRequestDto {
    private String title;
    private String body;
}
