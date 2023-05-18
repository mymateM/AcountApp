package com.connect.accountApp.domain.household.exception;

import com.connect.accountApp.global.error.ErrorCode;
import com.connect.accountApp.global.error.exception.BusinessException;

public class HouseholdNotFoundException extends BusinessException {

  public HouseholdNotFoundException(String message,
      ErrorCode errorCode) {
    super(message, errorCode);
  }
}
