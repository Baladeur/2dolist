package com.wcs._2dolist.dto;

public class AccessTokenResponseDTO extends ApiResponse {
    private String accessToken;

    public AccessTokenResponseDTO() {
    }

    public AccessTokenResponseDTO(String message, boolean success) {
        super(message, success);
    }

    public AccessTokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
