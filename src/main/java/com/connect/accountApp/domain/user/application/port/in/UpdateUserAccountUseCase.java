package com.connect.accountApp.domain.user.application.port.in;

import com.connect.accountApp.domain.user.adapter.in.web.request.UpdateUserAccountRequest;

public interface UpdateUserAccountUseCase {

    void updateUserAccount(String userEmail, UpdateUserAccountRequest request);
}
