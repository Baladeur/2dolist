package com.wcs._2dolist.config.application.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;
    private long registrationTokenLifetimeMillis;
    private long accessTokenLifetimeMillis;
    private long refreshTokenLifetimeMillis;

    public JwtProperties() {
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getRegistrationTokenLifetimeMillis() {
        return registrationTokenLifetimeMillis;
    }

    public void setRegistrationTokenLifetimeMillis(long registrationTokenLifetimeMillis) {
        this.registrationTokenLifetimeMillis = registrationTokenLifetimeMillis;
    }

    public long getAccessTokenLifetimeMillis() {
        return accessTokenLifetimeMillis;
    }

    public void setAccessTokenLifetimeMillis(long accessTokenLifetimeMillis) {
        this.accessTokenLifetimeMillis = accessTokenLifetimeMillis;
    }

    public long getRefreshTokenLifetimeMillis() {
        return refreshTokenLifetimeMillis;
    }

    public void setRefreshTokenLifetimeMillis(long refreshTokenLifetimeMillis) {
        this.refreshTokenLifetimeMillis = refreshTokenLifetimeMillis;
    }
}
