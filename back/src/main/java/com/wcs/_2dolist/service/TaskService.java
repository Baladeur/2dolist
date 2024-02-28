package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.TaskDTO;
import com.wcs._2dolist.entity.Task;
import com.wcs._2dolist.entity.TaskList;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.exception.ResourceNotFoundException;
import com.wcs._2dolist.exception.UserAccessException;
import com.wcs._2dolist.repository.TaskListRepository;
import com.wcs._2dolist.repository.TaskRepository;
import com.wcs._2dolist.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public TaskService(TaskRepository taskRepository, TaskListRepository taskListRepository, UserRepository userRepository, JwtService jwtService, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    private void verifyUserAccessToTaskList(Long taskListId, String accessToken) {
        String userEmail = jwtService.extractUserEmail(accessToken);
        User user = userRepository.findByEmail(userEmail);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + userEmail);
        }

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList not found with id: " + taskListId));

        if (!user.getWorkspaces().contains(taskList.getWorkspace())) {
            throw new UserAccessException("User does not have access to the task list's workspace.");
        }
    }

    public TaskDTO getTaskById(Long id, String accessToken) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with this id: " + id));

        verifyUserAccessToTaskList(task.getTaskList().getId(), accessToken);

        return modelMapper.map(task, TaskDTO.class);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO, String accessToken) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        verifyUserAccessToTaskList(existingTask.getTaskList().getId(), accessToken);

        existingTask.setName(taskDTO.getName());
        existingTask.setShortName(taskDTO.getShortName());
        existingTask.setColor(taskDTO.getColor());
        existingTask.setPriorityColor(taskDTO.getPriorityColor());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setDateLastActivity(taskDTO.getDateLastActivity());
        existingTask.setStart(taskDTO.getStart());
        existingTask.setEnd(taskDTO.getEnd());

        Task updatedTask = taskRepository.save(existingTask);
        return modelMapper.map(updatedTask, TaskDTO.class);
    }

    public void deleteTask(Long id, String accessToken) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        verifyUserAccessToTaskList(task.getTaskList().getId(), accessToken);

        taskRepository.deleteById(id);
    }

    public List<TaskDTO> getTasksByTaskListId(Long taskListId, String accessToken) {
        verifyUserAccessToTaskList(taskListId, accessToken);

        List<Task> tasks = taskRepository.findAllByTaskListId(taskListId);
        if (tasks.isEmpty()) {
            throw new ResourceNotFoundException("No tasks found for task list with id: " + taskListId);
        }
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO taskDTO, String accessToken) {
        Long taskListId = taskDTO.getTaskListId();
        verifyUserAccessToTaskList(taskListId, accessToken);

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList not found with id: " + taskListId));

        Task task = modelMapper.map(taskDTO, Task.class);
        task.setTaskList(taskList);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskDTO.class);
    }

}
