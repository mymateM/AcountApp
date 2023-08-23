package com.connect.accountApp.domain.user.application.port.in;

import com.connect.accountApp.domain.user.adapter.in.web.request.RegisterRequest;
import com.connect.accountApp.domain.user.adapter.in.web.response.AuthenticationResponse;

public interface RegisterUseCase {

  AuthenticationResponse register(RegisterRequest request);

}
