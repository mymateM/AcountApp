package com.connect.accountApp.domain.user.application.port.in;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ReissueAccessTokenUseCase {

  void reissueToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
