package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.adapter.in.web.response.GetBudgetAllowanceAmountResponse;
import com.connect.accountApp.domain.household.adapter.in.web.response.GetHouseholdBudgetResponse;
import com.connect.accountApp.domain.household.application.port.in.GetHouseholdBudgetUseCase;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/household")
@RequiredArgsConstructor
public class GetHouseholdBudgetController {

    private final GetHouseholdBudgetUseCase getHouseholdBudgetUseCase;

    @GetMapping("/budget")
    public ResponseEntity getBudgetAllowanceAmount(@AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();
        Household householdBudget = getHouseholdBudgetUseCase.getHouseholdBudget(userEmail);
        GetHouseholdBudgetResponse response = new GetHouseholdBudgetResponse(
                householdBudget.getHouseholdBudget(),
                householdBudget.getHouseholdBudgetAllowanceRatio());

        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
    }

}
