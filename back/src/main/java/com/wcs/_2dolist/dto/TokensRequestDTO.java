package com.wcs._2dolist.dto;

public class TokensRequestDTO extends ApiResponse {

    private String accessToken;
    private String refreshToken;

    public TokensRequestDTO() {
    }

    public TokensRequestDTO(String message, boolean success) {
        super(message, success);
    }

    public TokensRequestDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

