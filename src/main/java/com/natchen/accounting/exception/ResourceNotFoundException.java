package com.natchen.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ServiceException {
    /**
     * Resource not found exception handler.
     * @param message exception description.
     */
    public ResourceNotFoundException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
        this.setErrorCode("USER_INFO_NOT_FOUND");
        this.setErrorType(ErrorType.Client);
    }
}
