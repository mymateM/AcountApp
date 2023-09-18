package com.connect.accountApp.domain.expense.application.port.out;

import com.connect.accountApp.domain.expense.application.port.out.command.DailyTotalExpensesCommand;
import java.time.LocalDate;
import java.util.List;

public interface FindDailyTotalExpensesPort {

  List<DailyTotalExpensesCommand> FindDailyTotalExpenses(Long householdId, LocalDate date);

}
