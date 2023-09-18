package com.connect.accountApp.domain.user.application.port.out;

public interface GetUserSocialEmailPort {

  String getUserSocialEmail(String socialAuthType, String socialAuthAccessToken);

}
