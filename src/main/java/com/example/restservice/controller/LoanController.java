package com.example.restservice.controller;

import com.example.restservice.exception.dto.ErrorDetailsDTO;
import com.example.restservice.service.LoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

import javax.validation.Valid;

@Api
@RestController("/loans")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@GetMapping(path = "loan/{loanId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieves loan by id", response = Loan.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = Loan.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorDetailsDTO.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetailsDTO.class)
	})
	public ResponseEntity<Loan> getLoan(@PathVariable final Long loanId) {
		return new ResponseEntity<>(this.loanService.getLoan(loanId), HttpStatus.OK);
	}

	@PostMapping(path = "loan-metric/{loanId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Calculates loan metric", response = LoanMetric.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = LoanMetric.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorDetailsDTO.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetailsDTO.class)
	})
	public ResponseEntity<LoanMetric> calculateLoanMetric(@PathVariable final Long loanId) {
		return new ResponseEntity<>(this.loanService.calculateLoanMetric(loanId), HttpStatus.OK);
	}

	@PostMapping(path = "loan-metric", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Calculates loan metric", response = LoanMetric.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = LoanMetric.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorDetailsDTO.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetailsDTO.class)
	})
	public ResponseEntity<LoanMetric> calculateLoanMetric(@RequestBody @Valid final Loan loan) {
		return new ResponseEntity<>(this.loanService.calculateLoanMetric(loan), HttpStatus.OK);
	}

	@GetMapping(path = "loan/max-month-payment", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieves the max month payment loan", response = Loan.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = Loan.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorDetailsDTO.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetailsDTO.class)
	})
	public ResponseEntity<Loan> getMaxMonthlyPaymentLoan() {
		return new ResponseEntity<>(this.loanService.getMaxMonthlyPaymentLoan(), HttpStatus.OK);
	}

}
