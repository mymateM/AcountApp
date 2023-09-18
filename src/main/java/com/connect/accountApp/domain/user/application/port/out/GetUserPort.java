package com.connect.accountApp.domain.user.application.port.out;

import com.connect.accountApp.domain.user.domain.model.User;

public interface GetUserPort {
  User getUser(Long userId);

  User findUser(String email);

  User findUserWithHousehold(String email);
}
