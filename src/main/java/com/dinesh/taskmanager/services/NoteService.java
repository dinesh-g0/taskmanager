package com.dinesh.taskmanager.services;

import com.dinesh.taskmanager.entities.Note;
import com.dinesh.taskmanager.entities.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class NoteService {
    private final TaskService taskService;
    private final HashMap<Integer, TaskNotesHolder> taskNotesMap = new HashMap<>();

    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }

     static class TaskNotesHolder {
         protected int noteId = 1;
         protected ArrayList<Note> notes = new ArrayList<>();
     }

         public Note addNotesToTheTaskId(int taskId, String title, String body) {
             Task task = taskService.getTaskById(taskId);
             if (task == null) {
                 return null;
             }
             if (taskNotesMap.get(taskId) == null) {
                 taskNotesMap.put(taskId, new TaskNotesHolder());
             }
             TaskNotesHolder taskNotesHolder = taskNotesMap.get(taskId);
             Note note = new Note();
             note.setId(taskNotesHolder.noteId);
             note.setTitle(title);
             note.setBody(body);
             taskNotesHolder.notes.add(note);
             taskNotesHolder.noteId++;

             return note;
         }

        public ArrayList<Note> getNotesByTaskId(int taskId) {
            Task task = taskService.getTaskById(taskId);
            if (task == null) {
                return null;
            }

            if (taskNotesMap.get(taskId) == null) {
                taskNotesMap.put(taskId, new TaskNotesHolder());
            }

            return taskNotesMap.get(taskId).notes;
        }

}
