package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.application.port.in.GetUserDeviceTokenUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserDeviceTokenService implements GetUserDeviceTokenUseCase {

  private final GetUserPort getUserPort;

  @Override
  public String getUserDeviceToken(String userEmail) {

    User user = getUserPort.findUser(userEmail);
    return user.getDeviceToken();
  }
}
