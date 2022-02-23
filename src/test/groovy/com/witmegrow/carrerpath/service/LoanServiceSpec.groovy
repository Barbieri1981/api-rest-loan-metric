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

    def setup() {
        service = new LoanService()
        service.loanMetricFactory = factory
        consumerLoanMetricCalculator = Mock(ConsumerLoanMetricCalculator)
    }

    def "get loan"() {
        given: "given the following data"
        def loan = LoanGeneratonUtil.createLoan(1)
        service.getLoan(1) >> loan
        when: "invoke get loan"
        def response = service.getLoan(1)
        then: "verify response"
        response.getAnnualInterest() != null
    }

    def "calculate loan metric"() {
        given: "given the following data"
        def loan = LoanGeneratonUtil.createLoan(1)
        service.getLoan(1) >> loan
        def loanMetric = new LoanMetric(1,1);
        consumerLoanMetricCalculator.getLoanMetric(_) >> loanMetric
        factory.getInstance(_) >> consumerLoanMetricCalculator
        when: "invoke calculate loan metric"
        def response = service.calculateLoanMetric(1)
        then: "verify response"
        response.getMonthlyInterestRate() != null
        response.getMonthlyInterestRate()  == loanMetric.getMonthlyInterestRate()
    }
}
