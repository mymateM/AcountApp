package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.adapter.in.web.request.UserEnrollmentRequest;
import com.connect.accountApp.domain.user.application.port.in.UserEnrollmentUseCase;
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
public class UpdateUserController {

  private final UserEnrollmentUseCase userEnrollmentUseCase;

  @PostMapping("/update")
  public ResponseEntity enrollUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserEnrollmentRequest request) {

    String userEmail = userDetails.getUsername();
    userEnrollmentUseCase.enrollUser(userEmail, request);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
  }

}
