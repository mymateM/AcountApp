package com.connect.accountApp.domain.expense.exception;

import static com.connect.accountApp.global.error.ErrorCode.EXPENSE_NOT_FOUND;

import com.connect.accountApp.global.error.exception.BusinessException;

public class ExpenseNotFoundException extends BusinessException {

  public ExpenseNotFoundException(String message) {
    super(message, EXPENSE_NOT_FOUND);
  }

}
