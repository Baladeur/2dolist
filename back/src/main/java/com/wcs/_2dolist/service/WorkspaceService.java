package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.WorkspaceDTO;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.entity.UserWorkspace;
import com.wcs._2dolist.entity.Workspace;
import com.wcs._2dolist.repository.UserRepository;
import com.wcs._2dolist.repository.UserWorkspaceRepository;
import com.wcs._2dolist.repository.WorkspaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkspaceService {

    final private WorkspaceRepository workspaceRepository;
    final private UserWorkspaceRepository userWorkspaceRepository;
    final private UserRepository userRepository;
    final private JwtService jwtService;
    final private ModelMapper modelMapper;

    public WorkspaceService(
            WorkspaceRepository workspaceRepository,
            UserWorkspaceRepository userWorkspaceRepository,
            UserRepository userRepository,
            JwtService jwtService,
            ModelMapper modelMapper
    ) {
        this.workspaceRepository = workspaceRepository;
        this.userWorkspaceRepository = userWorkspaceRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    public List<WorkspaceDTO> getAllWorkspaces(String accessToken) {
        String userEmail = jwtService.extractUserEmail(accessToken);

        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + userEmail);
        }

        List<Workspace> userWorkspaces = user.getWorkspaces().stream()
                .toList();

        return userWorkspaces.stream()
                .map(workspace -> modelMapper.map(workspace, WorkspaceDTO.class))
                .collect(Collectors.toList());
    }


    public WorkspaceDTO getWorkspaceById(Long id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace not found with id: " + id));
        return modelMapper.map(workspace, WorkspaceDTO.class);
    }

    public WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO, String accessToken) {
        String userEmail = jwtService.extractUserEmail(accessToken);

        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + userEmail);
        }

        Workspace workspace = modelMapper.map(workspaceDTO, Workspace.class);
        Workspace savedWorkspace = workspaceRepository.save(workspace);

        UserWorkspace userWorkspace = new UserWorkspace();
        userWorkspace.setWorkspace(savedWorkspace);
        userWorkspace.setUser(user);
        userWorkspace.setPermissionLevel(1);
        userWorkspaceRepository.save(userWorkspace);

        return modelMapper.map(savedWorkspace, WorkspaceDTO.class);
    }


    public WorkspaceDTO updateWorkspace(Long id, WorkspaceDTO workspaceDTO) {
        Workspace existingWorkspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace not found with id: " + id));
        modelMapper.map(workspaceDTO, existingWorkspace);
        Workspace updatedWorkspace = workspaceRepository.save(existingWorkspace);
        return modelMapper.map(updatedWorkspace, WorkspaceDTO.class);
    }

    public void deleteWorkspace(Long id) {

        workspaceRepository.deleteById(id);
    }
}
