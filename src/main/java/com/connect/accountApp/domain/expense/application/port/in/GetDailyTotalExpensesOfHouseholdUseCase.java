package com.connect.accountApp.domain.expense.application.port.in;

import com.connect.accountApp.domain.expense.application.port.out.command.DailyTotalExpensesCommand;
import java.time.LocalDate;
import java.util.List;

public interface GetDailyTotalExpensesOfHouseholdUseCase {

  List<DailyTotalExpensesCommand> getDailyTotalExpensesOfHousehold(String userEmail, LocalDate date);

}
