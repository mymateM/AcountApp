package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.adapter.in.web.response.GetUserDeviceTokenResponse;
import com.connect.accountApp.domain.user.adapter.in.web.response.GetUserDeviceTokenResponse.GetUserDeviceTokenResponseBuilder;
import com.connect.accountApp.domain.user.application.port.in.GetUserDeviceTokenUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class GetUserDeviceTokenController {

  private final GetUserDeviceTokenUseCase getUserDeviceTokenUseCase;

  @GetMapping("/device-token")
  public ResponseEntity getUserDeviceToken(@AuthenticationPrincipal UserDetails userDetails) {

    String userEmail = userDetails.getUsername();

    String userDeviceToken = getUserDeviceTokenUseCase.getUserDeviceToken(userEmail);
    GetUserDeviceTokenResponse response = GetUserDeviceTokenResponse.builder().deviceToken(userDeviceToken).build();
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
