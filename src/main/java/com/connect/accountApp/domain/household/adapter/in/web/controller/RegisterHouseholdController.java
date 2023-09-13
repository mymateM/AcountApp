package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.adapter.in.web.controller.response.RegisterHouseholdResponse;
import com.connect.accountApp.domain.household.adapter.in.web.request.RegisterHouseholdRequest;
import com.connect.accountApp.domain.household.application.port.in.RegisterHouseholdUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/household")
@RequiredArgsConstructor
public class RegisterHouseholdController {

  private final RegisterHouseholdUseCase registerHouseholdUseCase;

  @PostMapping("/register")
  public ResponseEntity registerHousehold(@AuthenticationPrincipal UserDetails userDetails,
      @RequestBody RegisterHouseholdRequest request) {

    String userEmail = userDetails.getUsername();
    String inviteCode = registerHouseholdUseCase.registerHousehold(userEmail, request);
    RegisterHouseholdResponse response = new RegisterHouseholdResponse(inviteCode);

    return ResponseEntity.ok(SuccessResponse.create201CreatedResponse(response));
  }


}
