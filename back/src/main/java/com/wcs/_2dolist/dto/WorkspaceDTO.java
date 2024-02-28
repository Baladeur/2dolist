package com.wcs._2dolist.dto;

import com.wcs._2dolist.enums.WorkspaceVisibility;
import java.util.Set;

public class WorkspaceDTO {

    private Long id;
    private String name;
    private String color;
    private String background;
    private String description;
    private WorkspaceVisibility visibility;
    private Set<Long> userIds;

    public WorkspaceDTO() {
    }

    public WorkspaceDTO(Long id, String name, String color, String background, String description, WorkspaceVisibility visibility, Set<Long> userIds) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.background = background;
        this.description = description;
        this.visibility = visibility;
        this.userIds = userIds;
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

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WorkspaceVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(WorkspaceVisibility visibility) {
        this.visibility = visibility;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }
}
