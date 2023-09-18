package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.application.port.in.UserPasswordValidateUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserPasswordValidateService implements UserPasswordValidateUseCase {

  private final GetUserPort getUserPort;
  private final BCryptPasswordEncoder passwordEncoder;


  @Override
  public Boolean validatePassword(String userEmail, String password) {
    User user = getUserPort.findUser(userEmail);
    return passwordEncoder.matches(password, user.getUserPassword());
  }


}
