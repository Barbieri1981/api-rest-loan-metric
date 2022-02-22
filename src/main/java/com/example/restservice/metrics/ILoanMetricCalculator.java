package com.example.restservice.metrics;

import com.example.restservice.model.Borrower;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanType;

import java.util.function.Predicate;

public interface ILoanMetricCalculator {

	public static final int MIN_AGE = 18;
	public static final int MAX_AGE = 30;
	public static final Predicate<Borrower> validAge = response -> response.getAge() > MIN_AGE && response.getAge() < MAX_AGE;

	/**
	 * Validates if a loan is supported to calculate metrics
	 * 
	 * @param loan the loan {@link Loan}
	 */
	public default boolean isSupported(final Loan loan) {
		// Validate if the loan type is supported
		final LoanType loanType = LoanType.resolveByLoanTypeId(loan.getType());
		if (loanType == null){
			return false;
		}
		if (loanType == LoanType.LOAN_TYPE_STUDENT) {
			if (!validAge.test(loan.getBorrower())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Calculates the Loan Metric of a Loan entity
	 * 
	 * @param loan the loan {@link Loan}
	 */
	public LoanMetric getLoanMetric(final Loan loan);

}
