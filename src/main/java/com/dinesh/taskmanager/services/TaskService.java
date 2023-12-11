package com.dinesh.taskmanager.services;

import com.dinesh.taskmanager.entities.Task;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    @Getter
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int taskId = 1;

    public Task addTask(String title, String description, String deadLine) {
        Task task = new Task();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
       // task.setDeadLine(new Date(deadLine));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;

        return task;
    }

    public Task getTaskById(Integer id) {
        for (Task t :
                tasks) {
            if (t.getId() == id) {
                return t;
            }
            }
        return null;
    }
}
