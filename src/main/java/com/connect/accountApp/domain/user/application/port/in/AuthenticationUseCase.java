package com.connect.accountApp.domain.user.application.port.in;

import com.connect.accountApp.domain.user.adapter.in.web.request.AuthenticationRequest;
import com.connect.accountApp.domain.user.adapter.in.web.response.AuthenticationResponse;

public interface AuthenticationUseCase {

  AuthenticationResponse authenticate(AuthenticationRequest request);

}
