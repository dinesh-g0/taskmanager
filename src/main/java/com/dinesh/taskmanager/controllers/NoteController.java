package com.dinesh.taskmanager.controllers;

import com.dinesh.taskmanager.dtos.CreateNoteRequestDto;
import com.dinesh.taskmanager.dtos.CreateNoteResponseDto;
import com.dinesh.taskmanager.entities.Note;
import com.dinesh.taskmanager.services.NoteService;
import com.dinesh.taskmanager.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {
    private NoteService noteService;
    private TaskService taskService;

    public NoteController(NoteService noteService, TaskService taskService) {
        this.noteService = noteService;
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Note>> getNotes(@PathVariable("taskId") Integer taskId) {
        var notes = noteService.getNotesByTaskId(taskId);

        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDto> addNoteToTheTask(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteRequestDto createNoteRequestDto) {
        var note = noteService.addNotesToTheTaskId(
                taskId,
                createNoteRequestDto.getTitle(),
                createNoteRequestDto.getBody()
        );

        var createNoteResDto = new CreateNoteResponseDto();
        createNoteResDto.setNote(note);
        createNoteResDto.setTaskId(taskId);

        return ResponseEntity.ok(createNoteResDto);
    }
}
