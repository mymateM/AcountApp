package com.connect.accountApp.domain.user.application.port.out;

import com.connect.accountApp.domain.user.domain.model.User;

public interface GetUserPort {
  User getUser(Long userId);

}
