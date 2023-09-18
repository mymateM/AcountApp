package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.adapter.in.web.request.RegisterRequest;
import com.connect.accountApp.domain.user.adapter.in.web.response.AuthenticationResponse;
import com.connect.accountApp.domain.user.application.port.in.RegisterUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RegisterController {

  private final RegisterUseCase registerUseCase;

  @PostMapping("/register")
  public ResponseEntity register(@Valid @RequestBody RegisterRequest request) {
    AuthenticationResponse response = registerUseCase.register(request);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
