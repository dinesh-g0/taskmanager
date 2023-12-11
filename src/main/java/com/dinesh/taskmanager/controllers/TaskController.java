package com.dinesh.taskmanager.controllers;

import com.dinesh.taskmanager.dtos.CreateTaskRequestDto;
import com.dinesh.taskmanager.entities.Task;
import com.dinesh.taskmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping("")
    public ResponseEntity<ArrayList<Task>> getTasks() {
        ArrayList<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id) {
        Task task = taskService.getTaskById(id);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<Task> addTask(@RequestBody CreateTaskRequestDto body) {
        Task task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadLine());
        return ResponseEntity.ok(task);
    }
}
