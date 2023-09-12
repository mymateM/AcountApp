package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.adapter.in.web.request.SocialAuthenticationRequest;
import com.connect.accountApp.domain.user.application.port.out.GetSocialEmailOfUserUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserSocialEmailPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSocialEmailOfUserService implements GetSocialEmailOfUserUseCase {

  private final GetUserSocialEmailPort getUserSocialEmailPort;
  private final GetUserPort getUserPort;
  private final SaveUserPort saveUserPort;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public String getSocialEmailOfUser(SocialAuthenticationRequest request) {

    String userEmail = getUserSocialEmailPort.getUserSocialEmail(request.getSocialAuthType(), request.getSocialAccessToken());
    System.out.println(" getSocialEmailOfUser userEmail = " + userEmail);
    if (!getUserPort.userExist(userEmail)) {
      User user = User.builder().userEmail(userEmail).userPassword(passwordEncoder.encode("1234")).build();
      saveUserPort.save(user);
    }

    return userEmail;
  }
}
