package com.witmegrow.carrerpath.service

import com.example.restservice.metrics.LoanMetricFactory
import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator
import com.example.restservice.model.LoanMetric
import com.example.restservice.service.LoanService
import com.example.restservice.util.LoanGeneratonUtil
import spock.lang.Specification

class LoanServiceSpec extends Specification{

    LoanService service
    LoanMetricFactory factory = Mock(LoanMetricFactory)
    ConsumerLoanMetricCalculator consumerLoanMetricCalculator
    def loanId= 1
    def monthlyInterestRate = 1
    def monthlyPayment = 1

    def setup() {
        service = new LoanService()
        service.loanMetricFactory = factory
        consumerLoanMetricCalculator = Mock(ConsumerLoanMetricCalculator)
    }

    def "get loan"() {
        given: "given the following data"
        def loan = LoanGeneratonUtil.createLoan(loanId)
        service.getLoan(loanId) >> loan
        when: "invoke get loan"
        def response = service.getLoan(loanId)
        then: "verify response"
        response.getAnnualInterest() != null
    }

    def "calculate loan metric searching by id"() {
        given: "given the following data"
        def loan = LoanGeneratonUtil.createLoan(loanId)
        service.getLoan(loanId) >> loan
        def loanMetric = new LoanMetric(monthlyInterestRate,monthlyPayment);
        consumerLoanMetricCalculator.getLoanMetric(_) >> loanMetric
        factory.getInstance(_) >> consumerLoanMetricCalculator
        when: "invoke calculate loan metric"
        def response = service.calculateLoanMetric(loanId)
        then: "verify response"
        response.getMonthlyInterestRate() != null
        response.getMonthlyInterestRate()  == loanMetric.getMonthlyInterestRate()
        response.getMonthlyPayment() == loanMetric.getMonthlyPayment()
    }

    def "calculate loan metric"() {
        given: "given the following data"
        def loan = LoanGeneratonUtil.createLoan(loanId)
        def loanMetric = new LoanMetric(monthlyInterestRate,monthlyPayment);
        consumerLoanMetricCalculator.getLoanMetric(_) >> loanMetric
        factory.getInstance(_) >> consumerLoanMetricCalculator
        when: "invoke calculate loan metric"
        def response = service.calculateLoanMetric(loan)
        then: "verify response"
        response.getMonthlyInterestRate() != null
        response.getMonthlyInterestRate()  == loanMetric.getMonthlyInterestRate()
        response.getMonthlyPayment() == loanMetric.getMonthlyPayment()
    }
}
