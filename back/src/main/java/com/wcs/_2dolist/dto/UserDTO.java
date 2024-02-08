package com.wcs._2dolist.dto;


import java.util.Date;

public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String nickName;
    private String picture;
    private String address;
    private String status;
    private boolean emailVerified;
    private Date lastUpdatedDate;
    private String role;

    public UserDTO() {
    }

    public UserDTO(
            String email,
            String firstName,
            String lastName,
            String nickName,
            String picture,
            String address,
            String status,
            boolean emailVerified,
            Date lastUpdatedDate,
            String role
    ) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.picture = picture;
        this.address = address;
        this.status = status;
        this.emailVerified = emailVerified;
        this.lastUpdatedDate = lastUpdatedDate;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
