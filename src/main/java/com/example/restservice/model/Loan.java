package com.example.restservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Loan {

	private Long loanId;
	@NotNull
	@Min(1)
	private Double requestedAmount;
	@NotNull
	@Min(1)
	private Integer termMonths;
	@NotNull
	@Min(1)
	private Double annualInterest;
	private String type;
	private Borrower borrower;

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public double getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(Double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public int getTermMonths() {
		return termMonths;
	}

	public void setTermMonths(Integer termMonths) {
		this.termMonths = termMonths;
	}

	public double getAnnualInterest() {
		return annualInterest;
	}

	public void setAnnualInterest(Double annualInterest) {
		this.annualInterest = annualInterest;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	@Override
	public String toString() {
		return "Loan{" +
				"loanId=" + loanId +
				", requestedAmount=" + requestedAmount +
				", termMonths=" + termMonths +
				", annualInterest=" + annualInterest +
				", type='" + type + '\'' +
				", borrower=" + borrower +
				'}';
	}
}