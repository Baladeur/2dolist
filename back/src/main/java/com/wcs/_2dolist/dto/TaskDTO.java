package com.wcs._2dolist.dto;

import java.util.Date;

public class TaskDTO {
    private Long id;
    private String name;
    private String shortName;
    private String color;
    private String priorityColor;
    private String description;
    private Long taskListId;
    private Long ownerId;
    private Date dateLastActivity;
    private Date start;
    private Date end;
    private Boolean closed;

    public TaskDTO() {
    }

    public TaskDTO(
            Long id,
            String name,
            String shortName,
            String color,
            String priorityColor,
            String description,
            Long taskListId,
            Long ownerId,
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
        this.taskListId = taskListId;
        this.ownerId = ownerId;
        this.dateLastActivity = dateLastActivity;
        this.start = start;
        this.end = end;
        this.closed = closed;
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

    public Long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Long taskListId) {
        this.taskListId = taskListId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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
