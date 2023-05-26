package com.connect.accountApp.domain.expense.adapter.out.persistence;

import com.connect.accountApp.domain.expense.application.port.out.GetHouseholdTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.GetTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.user.application.port.out.GetUserSendMoneyPort;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExpensePersistenceAdapter implements GetTotalExpensePort, GetUserSendMoneyPort,
    GetHouseholdTotalExpensePort {

  private final ExpenseQueryRepository expenseQueryRepository;

  @Override
  public List<TotalExpenseCommand> getTotalExpense(Long householdId, LocalDateTime startTime,
      LocalDateTime endTime) {

    List<TotalExpenseCommand> totalExpenseQuery  = expenseQueryRepository.getTotalExpenseQuery(
        householdId, startTime, endTime);
    return totalExpenseQuery;
  }

  @Override
  public TotalExpenseCommand getUserSendMoney(Long userId, LocalDate date) {
    return expenseQueryRepository.getUserTotalExpenseQuery(userId, date);
  }

  @Override
  public int getHouseholdTotalExpense(Long householdId, LocalDateTime startTime, LocalDateTime endTime) {
    return expenseQueryRepository.getHouseholdTotalExpense(householdId, startTime, endTime);
  }
}
