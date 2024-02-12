package com.wcs._2dolist.config.application.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "registration-email")
public class RegistrationEmailProperties {

    private String frontDomain;

    public RegistrationEmailProperties() {
    }

    public String getFrontDomain() {
        return frontDomain;
    }

    public void setFrontDomain(String frontDomain) {
        this.frontDomain = frontDomain;
    }
}
