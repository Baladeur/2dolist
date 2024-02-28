package com.wcs._2dolist.entity;

import com.wcs._2dolist.enums.WorkspaceVisibility;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "workspace")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;
    private String background;
    private String description;

    private WorkspaceVisibility visibility = WorkspaceVisibility.PRIVATE;

    @ManyToMany(mappedBy = "workspaces")
    private Set<User> users;

    @OneToMany(mappedBy = "workspace")
    private Set<TaskList> taskLists;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserWorkspace> userWorkspaces;

    public Workspace() {
    }

    public Workspace(Long id, String name, String color, String background, String description, WorkspaceVisibility visibility, Set<User> users, Set<TaskList> taskLists, Set<UserWorkspace> userWorkspaces) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.background = background;
        this.description = description;
        this.visibility = visibility;
        this.users = users;
        this.taskLists = taskLists;
        this.userWorkspaces = userWorkspaces;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<TaskList> getTaskLists() {
        return taskLists;
    }

    public void setTaskLists(Set<TaskList> taskLists) {
        this.taskLists = taskLists;
    }

    public Set<UserWorkspace> getUserWorkspaces() {
        return userWorkspaces;
    }

    public void setUserWorkspaces(Set<UserWorkspace> userWorkspaces) {
        this.userWorkspaces = userWorkspaces;
    }
}
