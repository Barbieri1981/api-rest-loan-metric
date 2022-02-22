package com.example.restservice.exception;

import com.example.restservice.util.ErrorType;

public class LoanException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected final ErrorType error;
    protected final int httpStatus;

    public LoanException(final String message, final ErrorType error, final int httpStatus) {
        super(message);
        this.error = error;
        this.httpStatus = httpStatus;
    }

    public ErrorType getError() {
        return this.error;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

}
