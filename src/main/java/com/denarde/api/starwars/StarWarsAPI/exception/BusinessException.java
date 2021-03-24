package com.denarde.api.starwars.StarWarsAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusinessException extends ResponseStatusException {

    public BusinessException(HttpStatus status) {
        super(status);
    }

    public BusinessException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public BusinessException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
