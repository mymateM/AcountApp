package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.application.port.in.GetHouseholdUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/household")
@RequiredArgsConstructor
public class GetSettlementDateController {

    private final GetHouseholdUseCase getHouseholdUseCase;

    @GetMapping("/settlement-date")
    public ResponseEntity getSettlementDate(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        Integer settlementDayOfWeek = getHouseholdUseCase.getHouseholdSettlementDayOfWeek(userEmail);
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(settlementDayOfWeek));
    }

}
