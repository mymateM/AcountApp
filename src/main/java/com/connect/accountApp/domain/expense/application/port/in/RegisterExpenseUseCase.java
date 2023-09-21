package com.connect.accountApp.domain.expense.application.port.in;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.request.RegisterExpenseRequest;

public interface RegisterExpenseUseCase {

  Long registerExpense(String expenseDelegateUserEmail, RegisterExpenseRequest request);

}
