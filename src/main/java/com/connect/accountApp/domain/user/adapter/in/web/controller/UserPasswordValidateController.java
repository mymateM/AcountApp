package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.adapter.in.web.request.ValidatePasswordRequest;
import com.connect.accountApp.domain.user.adapter.in.web.response.ValidatePasswordResponse;
import com.connect.accountApp.domain.user.application.port.in.UserPasswordValidateUseCase;
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
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserPasswordValidateController {

  private final UserPasswordValidateUseCase userPasswordValidateUseCase;

  @PostMapping("/validate/password")
  public ResponseEntity validateUserPassword(@AuthenticationPrincipal UserDetails userDetails,
      @RequestBody ValidatePasswordRequest request) {

    Boolean isValidPassword = userPasswordValidateUseCase.validatePassword(userDetails.getUsername(), request.getPassword());

    ValidatePasswordResponse response = new ValidatePasswordResponse(isValidPassword);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
