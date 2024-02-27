package com.wcs._2dolist.dto;

public class TaskListDTO {
    private Long id;
    private String name;
    private String color;
    private String description;
    private Long workspaceId;

    public TaskListDTO() {
    }

    public TaskListDTO(Long id, String name, String color, String description, Long workspaceId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.description = description;
        this.workspaceId = workspaceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }
}