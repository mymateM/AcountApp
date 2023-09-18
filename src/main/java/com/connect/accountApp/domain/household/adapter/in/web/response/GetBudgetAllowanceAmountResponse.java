package com.connect.accountApp.domain.household.adapter.in.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBudgetAllowanceAmountResponse {

  @JsonProperty("household_budget_allowance_amount")
  private BigDecimal budgetAllowanceAmount;

}
