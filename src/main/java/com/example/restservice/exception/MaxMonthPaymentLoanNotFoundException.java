package com.example.restservice.exception;

import com.example.restservice.util.ErrorType;
import org.springframework.http.HttpStatus;

public class MaxMonthPaymentLoanNotFoundException extends LoanException {

    private static final long serialVersionUID = 1L;

    public MaxMonthPaymentLoanNotFoundException(final String message, final ErrorType error) {
        super(message, error, HttpStatus.NOT_FOUND.value());
    }

}
