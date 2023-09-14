package com.connect.accountApp.domain.user.exception;

import static com.connect.accountApp.global.error.ErrorCode.USER_EXISTED_HOUSEHOLD_MEMBER;

import com.connect.accountApp.global.error.exception.BusinessException;

public class ExistedHouseholdMemberException extends BusinessException {

  public ExistedHouseholdMemberException(String message) {
    super(message, USER_EXISTED_HOUSEHOLD_MEMBER);
  }

}
