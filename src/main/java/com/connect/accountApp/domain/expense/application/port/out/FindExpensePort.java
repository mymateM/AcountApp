package com.connect.accountApp.domain.expense.application.port.out;

import com.connect.accountApp.domain.expense.domain.model.Expense;

public interface FindExpensePort {

  Expense findExpense(Long expenseId);

}
