package com.connect.accountApp.domain.expense.application.port.out;

import com.connect.accountApp.domain.expense.domain.model.Expense;

public interface SaveExpensePort {

  Long saveExpensePort(Expense expense);

}
