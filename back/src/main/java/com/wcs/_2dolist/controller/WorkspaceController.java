package com.wcs._2dolist.controller;

import com.wcs._2dolist.dto.WorkspaceDTO;
import com.wcs._2dolist.service.WorkspaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping
    public List<WorkspaceDTO> getAllWorkspaces() {
        return workspaceService.getAllWorkspaces();
    }

    @GetMapping("/{id}")
    public WorkspaceDTO getWorkspaceById(@PathVariable Long id) {
        return workspaceService.getWorkspaceById(id);
    }

    @PostMapping
    public WorkspaceDTO createWorkspace(
            @RequestBody WorkspaceDTO workspaceDTO,
            @RequestHeader("Authorization") String accessToken
    ) {
        accessToken = accessToken.replace("Bearer ", "");

        return workspaceService.createWorkspace(workspaceDTO, accessToken);
    }

    @PutMapping("/{id}")
    public WorkspaceDTO updateWorkspace(@PathVariable Long id, @RequestBody WorkspaceDTO workspaceDTO) {
        return workspaceService.updateWorkspace(id, workspaceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkspace(@PathVariable Long id) {
        workspaceService.deleteWorkspace(id);
    }
}
