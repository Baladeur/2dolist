package com.wcs._2dolist.dto;

public class RegistrationRequestDTO {

    private String email;

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
