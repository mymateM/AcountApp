package com.connect.accountApp.domain.user.application.port.in;

import com.connect.accountApp.domain.user.domain.model.User;

public interface GetUserAccountUseCase {

    User getUserAccount(String userEmail);
}
