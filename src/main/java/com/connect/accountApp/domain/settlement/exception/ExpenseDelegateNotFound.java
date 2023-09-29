package com.connect.accountApp.domain.settlement.exception;

import static com.connect.accountApp.global.error.ErrorCode.EXPENSE_DELEGATE_USER_NOT_FOUND;

import com.connect.accountApp.global.error.exception.BusinessException;

public class ExpenseDelegateNotFound extends BusinessException {

  public ExpenseDelegateNotFound(String message) {
    super(message, EXPENSE_DELEGATE_USER_NOT_FOUND);
  }

}
