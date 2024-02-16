package com.wcs._2dolist.entity;


import com.wcs._2dolist.enums.UserStatus;
import com.wcs._2dolist.enums.UserRole;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String nickName;

    private String firstName;

    private String lastName;

    private String picture;

    private String address;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    private boolean emailVerified = false;

    private Date lastUpdatedDate;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    private String password;

    private Date dateRegistrationCompleted;

    private String registrationToken;

    private Date dateRequestRegistrationToken;

    private String refreshToken;

    private String accessToken;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PrivacySetting privacySetting;

    @ManyToMany
    @JoinTable(
            name = "user_workspace",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workspace_id")
    )
    private Set<Workspace> workspaces;

    @OneToMany(mappedBy = "owner")
    private Set<Task> tasks;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    public User() {
    }

    public User(
            Long id,
            String email,
            String nickName,
            String firstName,
            String lastName,
            String picture,
            String address,
            UserStatus status,
            boolean emailVerified,
            Date lastUpdatedDate,
            UserRole role,
            String password,
            String registrationToken,
            Date dateRequestRegistrationToken,
            PrivacySetting privacySetting,
            Set<Workspace> workspaces,
            Set<Task> tasks,
            Set<Comment> comments,
            Set<Notification> notifications,
            Date dateRegistrationCompleted,
            String refreshToken,
            String accessToken
    ) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.address = address;
        this.status = status;
        this.emailVerified = emailVerified;
        this.lastUpdatedDate = lastUpdatedDate;
        this.role = role;
        this.password = password;
        this.registrationToken = registrationToken;
        this.dateRequestRegistrationToken = dateRequestRegistrationToken;
        this.privacySetting = privacySetting;
        this.workspaces = workspaces;
        this.tasks = tasks;
        this.comments = comments;
        this.notifications = notifications;
        this.dateRegistrationCompleted = dateRegistrationCompleted;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public Date getDateRequestRegistrationToken() {
        return dateRequestRegistrationToken;
    }

    public void setDateRequestRegistrationToken(Date dateRequestRegistrationToken) {
        this.dateRequestRegistrationToken = dateRequestRegistrationToken;
    }

    public PrivacySetting getPrivacySetting() {
        return privacySetting;
    }

    public void setPrivacySetting(PrivacySetting privacySetting) {
        this.privacySetting = privacySetting;
    }

    public Set<Workspace> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(Set<Workspace> workspaces) {
        this.workspaces = workspaces;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Date getDateRegistrationCompleted() {
        return dateRegistrationCompleted;
    }

    public void setDateRegistrationCompleted(Date dateRegistrationCompleted) {
        this.dateRegistrationCompleted = dateRegistrationCompleted;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
