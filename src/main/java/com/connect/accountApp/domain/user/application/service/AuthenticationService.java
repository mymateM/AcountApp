package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.adapter.in.web.request.AuthenticationRequest;
import com.connect.accountApp.domain.user.adapter.in.web.response.AuthenticationResponse;
import com.connect.accountApp.domain.user.application.port.in.AuthenticationUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.Role;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.global.common.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {

  private final AuthenticationManager authenticationManager;
  private final GetUserPort findUserPort;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    User user = findUserPort.findUser(request.getEmail());

    String jwtToken = jwtService.generateToken(createUserDetails(user)); // UserDetail 객체가 들어가야함

    return AuthenticationResponse.builder()
        .token(jwtToken)
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
