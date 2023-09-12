package com.connect.accountApp.domain.user.application.port.out;

import com.connect.accountApp.domain.user.adapter.in.web.request.SocialAuthenticationRequest;

public interface GetSocialEmailOfUserUseCase {

  String getSocialEmailOfUser(SocialAuthenticationRequest request);

}
