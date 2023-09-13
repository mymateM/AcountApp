package com.connect.accountApp.domain.user.application.port.in;

import com.connect.accountApp.domain.user.adapter.in.web.request.UserEnrollmentRequest;

public interface UserEnrollmentUseCase {

  void enrollUser(String userEmail, UserEnrollmentRequest request);


}
