package com.example.restservice.metrics;

import com.example.restservice.exception.InvalidLoanTypeException;
import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator;
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;
import com.example.restservice.util.ErrorType;
import com.example.restservice.util.LoanType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanMetricFactory {

   private Map<LoanType, ILoanMetricCalculator> strategies = new EnumMap(LoanType.class);
   private final ConsumerLoanMetricCalculator consumerLoanMetricCalculator;
   private final StudentLoanMetricCalculator studentLoanMetricCalculator;

   /**
    * This method is used to get the implementation class by the loan type {@link Loan}.
    * If the implementation does not exist then it throws an invalid load type exception {@link InvalidLoanTypeException}
    *
    * @param loan the loan {@link Loan}
    * @return retrieves the loan metric calculator implementation class {@link ILoanMetricCalculator}
    */
   public ILoanMetricCalculator getInstance(Loan loan) {
      log.debug("Verifying is the loan type: {} is supported", loan.getType());
      if (this.consumerLoanMetricCalculator.isSupported(loan)) {
         return retrieveMetricCalculatroType(LoanType.resolveByLoanTypeId(loan.getType()));
      }
      throw new InvalidLoanTypeException("Loan type invalid", ErrorType.INVALID_LOAN_TYPE);
   }

   private ILoanMetricCalculator retrieveMetricCalculatroType(final LoanType loanType){
      log.debug("Retrieving metric calculator searching by loan type {}", loanType);
      if (loanType == null || !this.strategies.containsKey(loanType)){
         log.error("Invalid loan type {}", loanType);
         throw new InvalidLoanTypeException("Loan type invalid", ErrorType.INVALID_LOAN_TYPE);
      }
      return this.strategies.get(loanType);
   }

   @PostConstruct
   public void initStrategies() {
      log.debug("Loading loan type metric calculators");
      this.strategies.put(LoanType.LOAN_TYPE_STUDENT, this.studentLoanMetricCalculator);
      this.strategies.put(LoanType.LOAN_TYPE_CONSUMER, this.consumerLoanMetricCalculator);
   }

}
