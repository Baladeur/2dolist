package com.wcs._2dolist.dto;

public class RegistrationRequestDTO {

    private String email;
    private String frontUrl;

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(String email, String frontUrl) {
        this.email = email;
        this.frontUrl = frontUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }
}
