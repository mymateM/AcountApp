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

    if (existsUserPort.existsUserEmail(request.getEmail())) {
      throw new DuplicatedUserEmailException("[userEmail]" + request.getEmail() + "은 이미 존재합니다.");
    }

    User user = User.builder()
        .userNickname(request.getNickname())
        .userEmail(request.getEmail())
        .userPassword(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();


    String accessToken = jwtService.generateAccessToken(createUserDetails(user)); // UserDetail 객체가 들어가야함
    String refreshToken = jwtService.generateRefreshToken(createUserDetails(user));



    saveUserPort.save(user);

    return AuthenticationResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  private UserDetails createUserDetails(User user) {
    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUserEmail())
        .password(passwordEncoder.encode(user.getUserPassword()))
        .roles(Role.USER.name())
        .build();
  }
}
