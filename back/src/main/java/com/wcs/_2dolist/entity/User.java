package com.wcs._2dolist.entity;


import com.wcs._2dolist.enums.AccountStatus;
import com.wcs._2dolist.enums.UserRole;
import jakarta.persistence.*;
import java.util.Date;


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

    //  deleted, active, blocked
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    private boolean emailVerified = false;

    private String lastUpdated;

    @Column(length = 20)
    private UserRole role = UserRole.USER;

    private String passwordHash;

    private String registrationUrlHash;

    private Date dateRequestRegistrationUrlHash;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PrivacySetting privacySetting;

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
            AccountStatus accountStatus,
            boolean emailVerified,
            String lastUpdated,
            UserRole role,
            String passwordHash,
            String registrationUrlHash,
            Date dateRequestRegistrationUrlHash
    ) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.address = address;
        this.accountStatus = accountStatus;
        this.emailVerified = emailVerified;
        this.lastUpdated = lastUpdated;
        this.role = role;
        this.passwordHash = passwordHash;
        this.registrationUrlHash = registrationUrlHash;
        this.dateRequestRegistrationUrlHash = dateRequestRegistrationUrlHash;
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

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRegistrationUrlHash() {
        return registrationUrlHash;
    }

    public void setRegistrationUrlHash(String registrationUrlHash) {
        this.registrationUrlHash = registrationUrlHash;
    }

    public Date getDateRequestRegistrationUrlHash() {
        return dateRequestRegistrationUrlHash;
    }

    public void setDateRequestRegistrationUrlHash(Date dateRequestRegistrationUrlHash) {
        this.dateRequestRegistrationUrlHash = dateRequestRegistrationUrlHash;
    }

    public PrivacySetting getPrivacySetting() {
        return privacySetting;
    }

    public void setPrivacySetting(PrivacySetting privacySetting) {
        this.privacySetting = privacySetting;
    }
}
