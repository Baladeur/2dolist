package com.wcs._2dolist.dto;

public class RegistrationCompleteDTO {
    private String email;
    private String password;
    private String registrationToken;

    public RegistrationCompleteDTO() {
    }

    public RegistrationCompleteDTO(String email, String password, String registrationToken) {
        this.email = email;
        this.password = password;
        this.registrationToken = registrationToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
