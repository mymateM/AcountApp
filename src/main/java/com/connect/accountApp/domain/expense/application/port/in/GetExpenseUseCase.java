package com.connect.accountApp.domain.expense.application.port.in;

import com.connect.accountApp.domain.expense.domain.model.Expense;

public interface GetExpenseUseCase {

    Expense getExpense(Long expenseId);
}
