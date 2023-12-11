package com.dinesh.taskmanager.entities;

import lombok.Data;

import java.util.Date;
@Data
public class Task {
    private int id;
    private String title;
    private String description;
    private Date deadLine;
    private boolean completed;
}
