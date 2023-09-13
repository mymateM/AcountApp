package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.adapter.in.web.request.UserEnrollmentRequest;
import com.connect.accountApp.domain.user.application.port.in.UserEnrollmentUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEnrollmentService implements UserEnrollmentUseCase {

  private final GetUserPort getUserPort;
  private final SaveUserPort saveUserPort;

  @Override
  public void enrollUser(String userEmail, UserEnrollmentRequest request) {

    User user = getUserPort.findUser(userEmail);

    user.UpdateUserAtOnBoarding(request.getUserNickname(),
        request.getUserAccount().getAccountBank(), request.getUserAccount().getAccountNumber(), request.getUserProfileImage());

    saveUserPort.save(user);

  }
}
