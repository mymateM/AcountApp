package com.connect.accountApp.domain.user.application.port.in;

public interface UpdateUserDeviceTokenUseCase {

  void updateDeviceToken(String userEmail, String deviceToken);

}
