package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.adapter.in.web.request.RegisterRequest;
import com.connect.accountApp.domain.user.adapter.in.web.response.AuthenticationResponse;
import com.connect.accountApp.domain.user.application.port.in.RegisterUseCase;
import com.connect.accountApp.domain.user.application.port.out.ExistsUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.Role;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.domain.user.exception.DuplicatedUserEmailException;
import com.connect.accountApp.global.common.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {

  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final SaveUserPort saveUserPort;
  private final ExistsUserPort existsUserPort;

  @Override
  public AuthenticationResponse register(RegisterRequest request) {

    checkUserEmailDuplicate(request.getEmail());

    User defaultUser = createDefaultUser(request.getEmail(), request.getPassword());
    UserDetails userDetails = createUserDetails(defaultUser);

    String accessToken = jwtService.generateAccessToken(userDetails); // UserDetail 객체가 들어가야함
    String refreshToken = jwtService.generateRefreshToken(userDetails);

    saveUserPort.save(defaultUser);

    return createAuthenticationResponse(accessToken, refreshToken);
  }

  private UserDetails createUserDetails(User user) {
    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUserEmail())
        .password(passwordEncoder.encode(user.getUserPassword()))
        .roles(Role.USER.name())
        .build();
  }

  private void checkUserEmailDuplicate(String userEmail) {
    if (existsUserPort.existsUserEmail(userEmail)) {
      throw new DuplicatedUserEmailException("[userEmail] " + userEmail + "은 이미 존재합니다.");
    }
  }

  private User createDefaultUser(String userEmail, String userPassword) {
    return User.builder()
        .userEmail(userEmail)
        .userPassword(passwordEncoder.encode(userPassword))
        .role(Role.USER)
        .build();
  }

  private AuthenticationResponse createAuthenticationResponse(String accessToken, String refreshToken) {
    return AuthenticationResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
}
