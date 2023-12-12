package com.dinesh.taskmanager.dtos;

import com.dinesh.taskmanager.entities.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class CreateNoteResponseDto {
    private Integer taskId;
    private Note note;
}
