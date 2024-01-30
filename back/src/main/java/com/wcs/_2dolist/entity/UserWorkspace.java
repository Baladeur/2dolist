package com.wcs._2dolist.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "user_workspace")
public class UserWorkspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "permission_level")
    private int permissionLevel;

    public UserWorkspace() {
    }

    public UserWorkspace(Long id, Workspace workspace, User user, int permissionLevel) {
        this.id = id;
        this.workspace = workspace;
        this.user = user;
        this.permissionLevel = permissionLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}
