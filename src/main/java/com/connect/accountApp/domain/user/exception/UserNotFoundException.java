package com.connect.accountApp.domain.user.exception;

import com.connect.accountApp.global.error.ErrorCode;
import com.connect.accountApp.global.error.exception.BusinessException;

public class UserNotFoundException extends BusinessException {

  public UserNotFoundException(String message) {
    super(message, ErrorCode.USER_NOT_FOUND);
  }
}
