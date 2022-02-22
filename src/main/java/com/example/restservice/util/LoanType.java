package com.example.restservice.util;

import java.util.Arrays;

/**
 * Represents the loan types
 */
public enum LoanType {

    LOAN_TYPE_STUDENT("student"),
    LOAN_TYPE_CONSUMER("consumer");

    private String description;

    private LoanType(final String description) {
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Resolves the Loan Types using the provided type.
     *
     * @param loanTypeId loan type id
     * @return the loan type instance {@link LoanType} matching the type Id, NULL otherwise
     */
    public static LoanType resolveByLoanTypeId(final String loanTypeId) {
        return Arrays.stream(values()).filter(type -> type.getDescription().equals(loanTypeId)).findFirst().orElse(null);
    }
}
