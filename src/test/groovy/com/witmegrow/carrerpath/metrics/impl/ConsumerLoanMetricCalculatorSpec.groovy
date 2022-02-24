package com.witmegrow.carrerpath.metrics.impl

import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator
import com.example.restservice.model.Borrower
import com.example.restservice.model.Loan
import com.example.restservice.util.LoanType
import spock.lang.Specification

class ConsumerLoanMetricCalculatorSpec extends Specification{

    ConsumerLoanMetricCalculator consumer
    def loanId = 1
    def monthlyPayment = 17.09374744545464
    def monthlyInterestRate = 8.333333333333333E-4

    def setup() {
        consumer = new ConsumerLoanMetricCalculator()
    }

    def "get loan metric"() {
        given: "given the following data"
        def loan = createLoan(loanId)
        when: "invoke get instance"
        def response = consumer.getLoanMetric(loan)
        then: "verify response"
        response.getMonthlyPayment() == monthlyPayment
        response.getMonthlyInterestRate() == monthlyInterestRate
    }

    Loan createLoan(def loanId) {
        String loanType = LoanType.LOAN_TYPE_STUDENT.getDescription()
        Borrower borrower = new Borrower()
        borrower.setName("Borrower ")
        borrower.setAge(23)
        borrower.setAnnualIncome(1000)
        borrower.setDelinquentDebt(true)
        borrower.setAnnualDebt(100)
        borrower.setCreditHistory(50)

        Loan loan = new Loan()
        loan.setLoanId(loanId)
        loan.setRequestedAmount(1000 * loanId)
        loan.setTermMonths(loanId % 2 == 0 ? 36 : 60)
        loan.setAnnualInterest(1*loanId);
        loan.setType(loanType)
        loan.setBorrower(borrower)

        return loan
    }


}
