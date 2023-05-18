package com.connect.accountApp.domain.expense.adapter.out.persistence;

import com.connect.accountApp.domain.expense.application.port.out.GetTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExpensePersistenceAdapter implements GetTotalExpensePort {

  private final ExpenseQueryRepository expenseQueryRepository;

  @Override
  public List<TotalExpenseCommand> getTotalExpense(Long householdId, LocalDate date) {

    List<TotalExpenseCommand> totalExpenseQuery = expenseQueryRepository.getTotalExpenseQuery(
        householdId, date);
    return totalExpenseQuery;
  }
}
