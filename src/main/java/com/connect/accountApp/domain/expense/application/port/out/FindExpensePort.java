package com.connect.accountApp.domain.expense.application.port.out;

import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import java.time.LocalDate;
import java.util.List;

public interface FindExpensePort {

  Expense findExpense(Long expenseId);

  List<DailyExpenseCommand> findDailyExpenses(Long householdId, LocalDate date);

}
