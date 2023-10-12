package com.connect.accountApp.domain.bill.exception;

import static com.connect.accountApp.global.error.ErrorCode.ACTIVITY_NOTIFICATION_NOT_FOUND;

import com.connect.accountApp.global.error.exception.BusinessException;

public class NotFoundBillException extends BusinessException {

  public NotFoundBillException(String message) {
    super(message, ACTIVITY_NOTIFICATION_NOT_FOUND);
  }

}

