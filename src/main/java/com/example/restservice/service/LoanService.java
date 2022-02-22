package com.example.restservice.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.restservice.exception.InvalidDataException;
import com.example.restservice.exception.LoanException;
import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.util.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratonUtil;

@Slf4j
@Service
public class LoanService {

	@Autowired
	LoanMetricFactory loanMetricFactory;

	public Loan getLoan(final Long id) {
		return LoanGeneratonUtil.createLoan(id);
	}

	public LoanMetric calculateLoanMetric(final Loan loan) {
		log.debug("Calculating loan metric. Loan {}", loan);
		final ILoanMetricCalculator loanMetricCalculator = loanMetricFactory.getInstance(loan);
		return loanMetricCalculator.getLoanMetric(loan);
	}

	public LoanMetric calculateLoanMetric(final Long loanId) {
		log.debug("Searching loan by id {}", loanId);
		final Loan loan = getLoan(loanId);
		log.debug("Calculating loan metric. Loan {}", loan);
		return calculateLoanMetric(loan);
	}

	/**
	 * retrieves the loan with the max monthly payment
	 * @return {@link Loan}
	 */
	public Loan getMaxMonthlyPaymentLoan() {
		log.debug("Retrieving loans");
		final List<Loan> allLoans = LoanGeneratonUtil.getRandomLoans(20L);
		log.debug("Calculating loans metric");
		final Map<Loan, Double> loans = allLoans.stream()
				.collect(Collectors.toMap(loan -> loan, loan -> calculateLoanMetric(loan).getMonthlyPayment()));
		log.debug("Retrieving max monthly payment loan");
		return loans.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
	}


}
