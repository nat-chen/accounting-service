package com.natchen.accounting.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends ServiceException {

    /**
     * Invalid parameter exception handler.
     * @param message exception message
     */
    public InvalidParameterException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorCode("INVALID_PARAMETER");
        this.setErrorType(ErrorType.Client);
    }
}
