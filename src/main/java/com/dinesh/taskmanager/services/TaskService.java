package com.dinesh.taskmanager.services;

import com.dinesh.taskmanager.entities.Task;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    @Getter
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int taskId = 1;
    private SimpleDateFormat deadLineFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public Task addTask(String title, String description, String deadLine) throws ParseException {
        Task task = new Task();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
       task.setDeadLine(deadLineFormatter.parse(deadLine));
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

    public Task updateTask(int id, String description, String deadLine, Boolean completed) throws ParseException {
        Task task = getTaskById(id);
        if (task == null) {
            return null;
        }

        if (description != null) {
            task.setDescription(description);
        }

        if (deadLine != null) {
            task.setDeadLine(deadLineFormatter.parse(deadLine));
        }

        if (completed != null) {
            task.setCompleted(completed);
        }

        return task;
    }
}
