package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.expense.application.port.in.GetDailyExpenseUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDailyExpenseService implements GetDailyExpenseUseCase {

  private final GetUserPort getUserPort;
  private final FindExpensePort findExpensePort;

  @Override
  public List<DailyExpenseCommand> getDailyExpense(String userEmail, LocalDate date) {

    User user = getUserPort.findUserWithHousehold(userEmail);

    List<DailyExpenseCommand> DailyExpenseCommands = findExpensePort.findDailyExpenses(user.getHousehold().getHouseholdId(), date);

    return DailyExpenseCommands;
  }
}
