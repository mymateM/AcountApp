package com.connect.accountApp.domain.household.application.port.in;

import com.connect.accountApp.domain.household.adapter.in.web.response.GetBudgetAllowanceAmountResponse;
import java.math.BigDecimal;

public interface GetBudgetAllowanceAmountUseCase {

  GetBudgetAllowanceAmountResponse getBudgetAllowanceAmount(BigDecimal budgetAmount, Integer budgetAllowanceRatio);

}
