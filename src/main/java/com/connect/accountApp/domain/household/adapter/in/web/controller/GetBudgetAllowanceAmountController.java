package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.adapter.in.web.response.GetBudgetAllowanceAmountResponse;
import com.connect.accountApp.domain.household.application.port.in.GetBudgetAllowanceAmountUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/household")
@RequiredArgsConstructor
public class GetBudgetAllowanceAmountController {

  private final GetBudgetAllowanceAmountUseCase getBudgetAllowanceAmountUseCase;

  @GetMapping("/budget/allowance-amount")
  public ResponseEntity getBudgetAllowanceAmount(
      @RequestParam("budget-amount") BigDecimal budgetAmount,
      @RequestParam("allowance-ratio") Integer budgetAllowanceRatio) {

    GetBudgetAllowanceAmountResponse response = getBudgetAllowanceAmountUseCase.getBudgetAllowanceAmount(budgetAmount, budgetAllowanceRatio);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }


}
