package com.wcs._2dolist.controller;

import com.wcs._2dolist.dto.TaskListDTO;
import com.wcs._2dolist.service.TaskListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListDTO> getTaskListById(@PathVariable Long id, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        TaskListDTO taskListDTO = taskListService.getTaskListById(id, accessToken);
        return new ResponseEntity<>(taskListDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListDTO> updateTaskList(@PathVariable Long id, @RequestBody TaskListDTO taskListDTO, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        TaskListDTO updatedTaskList = taskListService.updateTaskList(id, taskListDTO, accessToken);
        return new ResponseEntity<>(updatedTaskList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long id, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        taskListService.deleteTaskList(id, accessToken);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<TaskListDTO>> getTaskListsByWorkspaceId(@PathVariable Long workspaceId, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        List<TaskListDTO> taskLists = taskListService.getTaskListsByWorkspaceId(workspaceId, accessToken);
        return new ResponseEntity<>(taskLists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskListDTO> createTaskList(@RequestBody TaskListDTO taskListDTO, @RequestHeader("Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        TaskListDTO createdTaskList = taskListService.createTaskList(taskListDTO, accessToken);
        return new ResponseEntity<>(createdTaskList, HttpStatus.CREATED);
    }
}
