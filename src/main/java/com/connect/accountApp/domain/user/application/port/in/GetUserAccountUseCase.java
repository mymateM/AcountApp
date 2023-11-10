package com.connect.accountApp.domain.user.application.port.in;

import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;

public interface GetUserAccountUseCase {

    User getUserAccount(String userEmail);

    List<User> getMembersAccount(String userEmail);
}
