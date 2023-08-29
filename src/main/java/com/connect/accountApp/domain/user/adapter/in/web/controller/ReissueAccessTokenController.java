package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.application.port.in.ReissueAccessTokenUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class ReissueAccessTokenController {

  private final ReissueAccessTokenUseCase reissueAccessTokenUseCase;

  @GetMapping("/reissue")
  public void reissueAccessToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    reissueAccessTokenUseCase.reissueToken(request, response);
  }

}
