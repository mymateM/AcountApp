package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.application.port.in.UpdateUserDeviceTokenUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserDeviceTokenService implements UpdateUserDeviceTokenUseCase {

  private final GetUserPort getUserPort;
  private final SaveUserPort saveUserPort;

  @Override
  public void updateDeviceToken(String userEmail, String deviceToken) {

    User user = getUserPort.findUserWithHousehold(userEmail);
    user.changeDeviceToken(deviceToken);
    saveUserPort.save(user);
  }
}
