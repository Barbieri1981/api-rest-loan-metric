package com.witmegrow.carrerpath.metrics

import com.example.restservice.exception.InvalidLoanTypeException
import com.example.restservice.metrics.LoanMetricFactory
import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator
import com.example.restservice.util.LoanGeneratonUtil
import spock.lang.Specification

class LoanMetricFactorySpec extends  Specification{

    ConsumerLoanMetricCalculator consumer = Mock(ConsumerLoanMetricCalculator)
    StudentLoanMetricCalculator student = Mock(StudentLoanMetricCalculator)
    LoanMetricFactory factory
    def loanIdConsumer = 1
    def loanIdStudent = 2

    def setup() {
        factory = new LoanMetricFactory(this.consumer, this.student)
        factory.initStrategies()
    }

    def "get instance consumer calculator"() {
        given: "given the following data"
        def loan = LoanGeneratonUtil.createLoan(loanIdConsumer)
        consumer.isSupported(_) >> true
        when: "invoke get instance"
        def response = factory.getInstance(loan)
        then: "verify response"
        response in consumer
    }

    def "get instance student calculator"() {
        given: "given the following data"
        def loan = LoanGeneratonUtil.createLoan(loanIdStudent)
        consumer.isSupported(_) >> true
        when: "invoke get instance"
        def response = factory.getInstance(loan)
        then: "verify response"
        response in student
    }

    def "when call get instance then throw exception"() {
        given: "given the following data"
        def loan = LoanGeneratonUtil.createLoan(loanIdStudent)
        consumer.isSupported(_) >> false
        when: "invoke get instance"
        factory.getInstance(loan)
        then: "verify exception"
        thrown(InvalidLoanTypeException)
    }


}
