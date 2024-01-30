package com.wcs._2dolist.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String shortName;
    private String color;
    private String priorityColor;
    private String description;

    @ManyToOne
    @JoinColumn(name = "tasklist_id")
    private TaskList taskList;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Temporal(TemporalType.DATE)
    private Date dateLastActivity;

    @Temporal(TemporalType.DATE)
    private Date start;

    @Temporal(TemporalType.DATE)
    private Date end;

    private Boolean closed;

    public Task() {
    }

    public Task(
        int id,
        String name,
        String shortName,
        String color,
        String priorityColor,
        String description,
        TaskList taskList,
        User owner,
        Date dateLastActivity,
        Date start,
        Date end,
        Boolean closed
    ) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.color = color;
        this.priorityColor = priorityColor;
        this.description = description;
        this.taskList = taskList;
        this.owner = owner;
        this.dateLastActivity = dateLastActivity;
        this.start = start;
        this.end = end;
        this.closed = closed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPriorityColor() {
        return priorityColor;
    }

    public void setPriorityColor(String priorityColor) {
        this.priorityColor = priorityColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDateLastActivity() {
        return dateLastActivity;
    }

    public void setDateLastActivity(Date dateLastActivity) {
        this.dateLastActivity = dateLastActivity;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}

