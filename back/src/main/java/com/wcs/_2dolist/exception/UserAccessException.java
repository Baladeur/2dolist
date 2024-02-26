package com.wcs._2dolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAccessException extends RuntimeException {
    public UserAccessException(String message) {
        super(message);
    }
}
