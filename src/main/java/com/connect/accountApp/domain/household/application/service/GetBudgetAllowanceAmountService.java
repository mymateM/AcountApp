package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.household.adapter.in.web.response.GetBudgetAllowanceAmountResponse;
import com.connect.accountApp.domain.household.application.port.in.GetBudgetAllowanceAmountUseCase;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBudgetAllowanceAmountService implements GetBudgetAllowanceAmountUseCase {

  @Override
  public GetBudgetAllowanceAmountResponse getBudgetAllowanceAmount(BigDecimal budgetAmount, Integer budgetAllowanceRatio) {

    BigDecimal budgetAllowanceAmount = budgetAmount.multiply(BigDecimal.valueOf((budgetAllowanceRatio) / 100.0));

    return GetBudgetAllowanceAmountResponse
        .builder()
        .budgetAllowanceAmount(budgetAllowanceAmount)
        .build();
  }
}
