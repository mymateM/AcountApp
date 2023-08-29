package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.adapter.in.web.response.AuthenticationResponse;
import com.connect.accountApp.domain.user.application.port.in.ReissueAccessTokenUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.Role;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.global.common.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReissueAccessTokenService implements ReissueAccessTokenUseCase {

  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final GetUserPort getUserPort;

  @Override
  public void reissueToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }

    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);

    if (userEmail != null) {
      UserDetails userDetails = createUserDetails(this.getUserPort.findUser(userEmail));

      if (jwtService.isTokenValid(refreshToken, userDetails)) {
        String accessToken = jwtService.generateAccessToken(userDetails);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();

        new ObjectMapper().writeValue(response.getOutputStream(), authenticationResponse);
      }
    }
  }

  private UserDetails createUserDetails(User user) {
    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUserEmail())
        .password(passwordEncoder.encode(user.getUserPassword()))
        .roles(Role.USER.name())
        .build();
  }
}
