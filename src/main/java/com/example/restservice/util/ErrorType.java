package com.example.restservice.util;

/**
 * Represents the errors of the application
 */
public enum ErrorType {

    GENERIC_ERROR("loan_001", "unexpected error"),
    ERROR_DATA("loan_002", "Error while validating data"),
    INVALID_LOAN_TYPE("loan_003", "The loan type is invalid"),
    INVALID_ANNUAL_INTEREST("loan_004", "Invalid annual interest");

    private final String code;
    private final String description;

    private ErrorType(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return new StringBuilder("Type Error [code: ").append(this.code).append(", description: ")
                .append(this.description).append("]").toString();
    }
}
