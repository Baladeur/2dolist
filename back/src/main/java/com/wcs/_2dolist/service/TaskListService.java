package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.TaskListDTO;
import com.wcs._2dolist.entity.TaskList;
import com.wcs._2dolist.entity.Workspace;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.exception.ResourceNotFoundException;
import com.wcs._2dolist.exception.UserAccessException;
import com.wcs._2dolist.repository.TaskListRepository;
import com.wcs._2dolist.repository.WorkspaceRepository;
import com.wcs._2dolist.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public TaskListService(TaskListRepository taskListRepository, WorkspaceRepository workspaceRepository, UserRepository userRepository, JwtService jwtService, ModelMapper modelMapper) {
        this.taskListRepository = taskListRepository;
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    private void verifyUserAccessToWorkspace(Long workspaceId, String accessToken) {
        String userEmail = jwtService.extractUserEmail(accessToken);
        User user = userRepository.findByEmail(userEmail);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + userEmail);
        }

        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found with id: " + workspaceId));

        if (!user.getWorkspaces().contains(workspace)) {
            throw new UserAccessException("User does not have access to the workspace.");
        }
    }

    public TaskListDTO getTaskListById(Long id, String accessToken) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList not found with this id: " + id));

        verifyUserAccessToWorkspace(taskList.getWorkspace().getId(), accessToken);

        return modelMapper.map(taskList, TaskListDTO.class);
    }

    public TaskListDTO updateTaskList(Long id, TaskListDTO taskListDTO, String accessToken) {
        TaskList existingTaskList = taskListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList not found with id: " + id));

        verifyUserAccessToWorkspace(existingTaskList.getWorkspace().getId(), accessToken);

        existingTaskList.setName(taskListDTO.getName());
        existingTaskList.setColor(taskListDTO.getColor());
        existingTaskList.setDescription(taskListDTO.getDescription());

        TaskList updatedTaskList = taskListRepository.save(existingTaskList);
        return modelMapper.map(updatedTaskList, TaskListDTO.class);
    }

    public void deleteTaskList(Long id, String accessToken) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList not found with id: " + id));

        verifyUserAccessToWorkspace(taskList.getWorkspace().getId(), accessToken);

        taskListRepository.deleteById(id);
    }

    public List<TaskListDTO> getTaskListsByWorkspaceId(Long workspaceId, String accessToken) {
        verifyUserAccessToWorkspace(workspaceId, accessToken);

        List<TaskList> taskLists = taskListRepository.findAllByWorkspaceId(workspaceId);
        if (taskLists.isEmpty()) {
            throw new ResourceNotFoundException("No task lists found for workspace with id: " + workspaceId);
        }
        return taskLists.stream()
                .map(taskList -> modelMapper.map(taskList, TaskListDTO.class))
                .collect(Collectors.toList());
    }

    public TaskListDTO createTaskList(TaskListDTO taskListDTO, String accessToken) {
        Long workspaceId = taskListDTO.getWorkspaceId();
        verifyUserAccessToWorkspace(workspaceId, accessToken);

        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found with id: " + workspaceId));

        TaskList taskList = modelMapper.map(taskListDTO, TaskList.class);
        taskList.setWorkspace(workspace);
        TaskList savedTaskList = taskListRepository.save(taskList);
        return modelMapper.map(savedTaskList, TaskListDTO.class);
    }

}
