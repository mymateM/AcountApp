package com.connect.accountApp.domain.expense.application.port.in;

import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import java.time.LocalDate;
import java.util.List;

public interface GetDailyExpenseUseCase {

  List<DailyExpenseCommand> getDailyExpense(String userEmail, LocalDate date);

}
