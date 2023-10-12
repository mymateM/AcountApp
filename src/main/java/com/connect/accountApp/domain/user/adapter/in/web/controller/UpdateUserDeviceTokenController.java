package com.connect.accountApp.domain.user.adapter.in.web.controller;


import com.connect.accountApp.domain.user.adapter.in.web.request.UpdateDeviceTokenRequest;
import com.connect.accountApp.domain.user.application.port.in.UpdateUserDeviceTokenUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UpdateUserDeviceTokenController {

  private final UpdateUserDeviceTokenUseCase updateUserDeviceTokenUseCase;

  @PostMapping("/device-token")
  public void UpdateUserDeviceToken(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateDeviceTokenRequest request) {

    log.info("device-token : {}", request.getDeviceToken());
    updateUserDeviceTokenUseCase.updateDeviceToken(userDetails.getUsername(), request.getDeviceToken());
  }

}
