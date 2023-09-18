package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.expense.application.port.in.GetDailyTotalExpensesOfHouseholdUseCase;
import com.connect.accountApp.domain.expense.application.port.out.FindDailyTotalExpensesPort;
import com.connect.accountApp.domain.expense.application.port.out.command.DailyTotalExpensesCommand;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDailyTotalExpensesOfHouseholdService implements GetDailyTotalExpensesOfHouseholdUseCase {

  private final FindDailyTotalExpensesPort findDailyTotalExpensesPort;
  private final GetUserPort getUserPort;

  @Override
  public List<DailyTotalExpensesCommand> getDailyTotalExpensesOfHousehold(String userEmail, LocalDate date) {

    User user = getUserPort.findUserWithHousehold(userEmail);


    List<DailyTotalExpensesCommand> dailyTotalExpensesCommand = findDailyTotalExpensesPort
        .FindDailyTotalExpenses(user.getHousehold().getHouseholdId(), date);

    return dailyTotalExpensesCommand;
  }
}
