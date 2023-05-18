package com.connect.accountApp.domain.expense.application.port.out;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import java.time.LocalDate;
import java.util.List;

public interface GetTotalExpensePort {

  List<TotalExpenseCommand> getTotalExpense(Long userId, LocalDate date);

}
