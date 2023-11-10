package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.adapter.in.web.response.GetHouseholdHomeResponse;
import com.connect.accountApp.domain.household.application.port.in.GetHouseholdHomeUseCase;
import com.connect.accountApp.domain.household.application.port.in.command.GetHouseholdHomeCommand;
import com.connect.accountApp.domain.household.application.port.in.command.GetUserHomeCommand;
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
public class GetHouseholdHomeController {

    private final GetHouseholdHomeUseCase getHouseholdHomeUseCase;


    @GetMapping("/home")
    public ResponseEntity getHouseholdHome(@AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();

        GetHouseholdHomeCommand householdHomeCommand = getHouseholdHomeUseCase.getHouseholdHome(userEmail);
        GetUserHomeCommand userHomeCommand = getHouseholdHomeUseCase.getUserHome(userEmail);

        GetHouseholdHomeResponse response = new GetHouseholdHomeResponse(householdHomeCommand, userHomeCommand);

        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
    }
}
