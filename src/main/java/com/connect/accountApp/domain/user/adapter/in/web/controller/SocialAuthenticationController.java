package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.adapter.in.web.request.AuthenticationRequest;
import com.connect.accountApp.domain.user.adapter.in.web.request.SocialAuthenticationRequest;
import com.connect.accountApp.domain.user.adapter.in.web.response.AuthenticationResponse;
import com.connect.accountApp.domain.user.application.port.in.AuthenticationUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetSocialEmailOfUserUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SocialAuthenticationController {

  private final GetSocialEmailOfUserUseCase getSocialEmailOfUserUseCase;
  private final AuthenticationUseCase authenticationUseCase;

  @PostMapping("/authenticate/social")
  public ResponseEntity authenticate(@RequestBody SocialAuthenticationRequest request) {

    String userSocialEmail = getSocialEmailOfUserUseCase.getSocialEmailOfUser(request);
    System.out.println("userSocialEmail = " + userSocialEmail);
    AuthenticationResponse response = authenticationUseCase.authenticate(
        new AuthenticationRequest(userSocialEmail, "1234"));

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
