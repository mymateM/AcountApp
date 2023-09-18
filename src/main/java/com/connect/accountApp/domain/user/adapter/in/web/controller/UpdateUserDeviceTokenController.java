package com.connect.accountApp.domain.user.adapter.in.web.controller;


import com.connect.accountApp.domain.user.adapter.in.web.request.UpdateDeviceTokenRequest;
import com.connect.accountApp.domain.user.application.port.in.UpdateUserDeviceTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UpdateUserDeviceTokenController {

  private final UpdateUserDeviceTokenUseCase updateUserDeviceTokenUseCase;

  @PostMapping("/device-token")
  public void UpdateUserDeviceToken(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateDeviceTokenRequest request) {


    updateUserDeviceTokenUseCase.updateDeviceToken(userDetails.getUsername(), request.getDeviceToken());
  }

}
