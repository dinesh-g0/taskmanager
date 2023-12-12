package com.dinesh.taskmanager.dtos;

import com.dinesh.taskmanager.entities.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskNotesResponseDto {
    private int id;
    private String title;
    private String description;
    private Date deadLine;
    private boolean completed;
    private ArrayList<Note> notes;
}
