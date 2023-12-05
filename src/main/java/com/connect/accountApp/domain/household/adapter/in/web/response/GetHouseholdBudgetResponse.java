package com.connect.accountApp.domain.household.adapter.in.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class GetHouseholdBudgetResponse {

    @JsonProperty("budget_amount")
    private BigDecimal householdBudgetAmount;
    @JsonProperty("budget_allowance")
    private Integer householdBudgetAllowance;

    public GetHouseholdBudgetResponse(BigDecimal householdBudgetAmount, Integer householdBudgetAllowance) {
        this.householdBudgetAmount = householdBudgetAmount.setScale(0, RoundingMode.FLOOR);
        this.householdBudgetAllowance = householdBudgetAllowance;
    }
}
