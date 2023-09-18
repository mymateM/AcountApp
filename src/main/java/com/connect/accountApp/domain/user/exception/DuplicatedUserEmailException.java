package com.connect.accountApp.domain.user.exception;

import static com.connect.accountApp.global.error.ErrorCode.USER_EMAIL_DUPLICATED;

import com.connect.accountApp.global.error.ErrorCode;
import com.connect.accountApp.global.error.exception.BusinessException;

public class DuplicatedUserEmailException extends BusinessException {

  public DuplicatedUserEmailException(String message) {
    super(message, USER_EMAIL_DUPLICATED);
  }
}
