package com.wcs._2dolist.controller;

import com.wcs._2dolist.dto.TaskDTO;
import com.wcs._2dolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        TaskDTO taskDTO = taskService.getTaskById(id, accessToken);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        TaskDTO updatedTask = taskService.updateTask(id, taskDTO, accessToken);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        taskService.deleteTask(id, accessToken);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/task-list/{taskListId}")
    public ResponseEntity<List<TaskDTO>> getTasksByTaskListId(@PathVariable Long taskListId, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        List<TaskDTO> tasks = taskService.getTasksByTaskListId(taskListId, accessToken);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        TaskDTO createdTask = taskService.createTask(taskDTO, accessToken);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
}
