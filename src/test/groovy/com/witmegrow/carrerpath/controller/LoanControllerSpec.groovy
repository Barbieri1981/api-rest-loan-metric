package com.witmegrow.carrerpath.controller

import com.example.restservice.controller.LoanController
import com.example.restservice.service.LoanService
import com.example.restservice.util.LoanGeneratonUtil
import org.springframework.http.HttpStatus
import spock.lang.Specification

class LoanControllerSpec extends Specification{

    LoanController controller
    LoanService service = Mock(LoanService)

    def setup() {
        controller = new LoanController()
        controller.loanService = service
    }

    def "get loan"() {
        given: "given the following data"
        def loan = LoanGeneratonUtil.createLoan(1)
        service.getLoan(1) >> loan
        when: "invoke get loan"
        def response = controller.getLoan(1)
        then: "verify response"
        response.getBody() != null && response.getStatusCode() == HttpStatus.OK
    }
}
