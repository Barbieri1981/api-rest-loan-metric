package com.example.restservice.metrics.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Slf4j
@Component
@Qualifier("ConsumerLoanMetricCalculator")
public class ConsumerLoanMetricCalculator implements ILoanMetricCalculator {

	/**
	 * Loan type = consumer
	 * Monthly Rate = 0.005
	 * Monthly Payment = (10000*0.005)/(1-(1+0.005)^-24) = 443.20
	 */
	@Override
	public LoanMetric getLoanMetric(final Loan loan) {
		log.debug("Retrieve loan metric: {}", loan);
		final Double monthlyInterestRate = calculateMonthlyInterestRate(loan);
		final Double monthlyPayment = calculateMonthlyPayment(loan, monthlyInterestRate);
		return new LoanMetric(monthlyInterestRate, monthlyPayment);
	}

	private Double calculateMonthlyPayment(final Loan loan, final Double monthlyInterestRate) {
		log.debug("calculate monthly payment");
		final int pow = (-1) * loan.getTermMonths();
		final Double monthlyPayment = (loan.getRequestedAmount() * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, pow));
		return monthlyPayment;
	}

	private Double calculateMonthlyInterestRate(final Loan loan) {
		log.debug("calculate monthly interest rate");
		final Double monthlyInterestRate = (loan.getAnnualInterest() /12 ) / 100;
		return monthlyInterestRate;
	}

}
