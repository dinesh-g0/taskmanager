package com.dinesh.taskmanager.controllers;

import com.dinesh.taskmanager.dtos.CreateTaskRequestDto;
import com.dinesh.taskmanager.dtos.ErrorResponseDto;
import com.dinesh.taskmanager.dtos.TaskNotesResponseDto;
import com.dinesh.taskmanager.dtos.UpdateTaskRequestDto;
import com.dinesh.taskmanager.entities.Task;
import com.dinesh.taskmanager.services.NoteService;
import com.dinesh.taskmanager.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private NoteService noteService;
    private ModelMapper mapper = new ModelMapper();

    @GetMapping("")
    public ResponseEntity<ArrayList<Task>> getTasks() {
        ArrayList<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskNotesResponseDto> getTaskById(@PathVariable("id") Integer id) {
        Task task = taskService.getTaskById(id);
        var notes = noteService.getNotesByTaskId(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var taskResponse = mapper.map(task, TaskNotesResponseDto.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable("id") Integer id, @RequestBody UpdateTaskRequestDto utrd) throws ParseException {
        Task task = taskService.updateTask(id, utrd.getDescription(), utrd.getDeadLine(), utrd.getCompleted());

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<Task> addTask(@RequestBody CreateTaskRequestDto body) throws ParseException {
        Task task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadLine());
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleErrors(Exception e) {
        if (e instanceof ParseException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Invalid Date Format"));
        }

        return ResponseEntity.internalServerError().body(new ErrorResponseDto("Internal Server Error"));
    }
}
