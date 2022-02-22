package com.example.restservice.exception;

import com.example.restservice.util.ErrorType;
import org.springframework.http.HttpStatus;

public class InvalidDataException extends LoanException {

    private static final long serialVersionUID = 1L;

    public InvalidDataException(final String message, final ErrorType error) {
        super(message, error, HttpStatus.BAD_REQUEST.value());
    }

}
