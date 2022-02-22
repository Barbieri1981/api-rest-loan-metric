package com.example.restservice.exception;

import com.example.restservice.util.ErrorType;
import org.springframework.http.HttpStatus;

public class InvalidLoanTypeException extends LoanException {

    private static final long serialVersionUID = 1L;

    public InvalidLoanTypeException(final String message, final ErrorType error) {
        super(message, error, HttpStatus.BAD_REQUEST.value());
    }

}
