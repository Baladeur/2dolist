package com.wcs._2dolist.entity;

import com.wcs._2dolist.enums.PrivacyLevel;
import jakarta.persistence.*;

@Entity
@Table(name = "privacy_setting")
public class PrivacySetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PrivacyLevel email = PrivacyLevel.PRIVATE;

    @Enumerated(EnumType.STRING)
    private PrivacyLevel nickName = PrivacyLevel.PRIVATE;

    @Enumerated(EnumType.STRING)
    private PrivacyLevel firstName = PrivacyLevel.PRIVATE;

    @Enumerated(EnumType.STRING)
    private PrivacyLevel lastName = PrivacyLevel.PRIVATE;

    @Enumerated(EnumType.STRING)
    private PrivacyLevel picture = PrivacyLevel.PRIVATE;

    @Enumerated(EnumType.STRING)
    private PrivacyLevel address = PrivacyLevel.PRIVATE;

    public PrivacySetting() {
    }

    public PrivacySetting(
        int id,
        User user,
        PrivacyLevel email,
        PrivacyLevel nickName,
        PrivacyLevel firstName,
        PrivacyLevel lastName,
        PrivacyLevel picture,
        PrivacyLevel address
    ) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PrivacyLevel getEmail() {
        return email;
    }

    public void setEmail(PrivacyLevel email) {
        this.email = email;
    }

    public PrivacyLevel getNickName() {
        return nickName;
    }

    public void setNickName(PrivacyLevel nickName) {
        this.nickName = nickName;
    }

    public PrivacyLevel getFirstName() {
        return firstName;
    }

    public void setFirstName(PrivacyLevel firstName) {
        this.firstName = firstName;
    }

    public PrivacyLevel getLastName() {
        return lastName;
    }

    public void setLastName(PrivacyLevel lastName) {
        this.lastName = lastName;
    }

    public PrivacyLevel getPicture() {
        return picture;
    }

    public void setPicture(PrivacyLevel picture) {
        this.picture = picture;
    }

    public PrivacyLevel getAddress() {
        return address;
    }

    public void setAddress(PrivacyLevel address) {
        this.address = address;
    }
}
