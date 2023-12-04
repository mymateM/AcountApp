package com.connect.accountApp.domain.expense.adapter.out.persistence;

import com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.ExpenseJpaRepository;
import com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.ExpenseJpaEntity;
import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.SearchedCondition;
import com.connect.accountApp.domain.expense.application.port.out.DeleteExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.FindDailyTotalExpensesPort;
import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.GetHouseholdTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.GetTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.SaveExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.DailyTotalExpensesCommand;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseByCategoryCommand;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.expense.exception.ExpenseNotFoundException;
import com.connect.accountApp.domain.user.application.port.out.GetUserSendMoneyPort;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExpensePersistenceAdapter implements GetTotalExpensePort, GetUserSendMoneyPort,
    GetHouseholdTotalExpensePort, FindDailyTotalExpensesPort, SaveExpensePort, FindExpensePort, DeleteExpensePort {

  private final ExpenseQueryRepository expenseQueryRepository;
  private final ExpenseJpaRepository expenseJpaRepository;
  private final ExpenseMapper expenseMapper;

  @Override
  public List<TotalExpenseCommand> getTotalExpense(Long householdId, LocalDateTime startTime,
      LocalDateTime endTime) {

    List<TotalExpenseCommand> totalExpenseQuery  = expenseQueryRepository.getTotalExpenseQuery(
        householdId, startTime.toLocalDate(), endTime.toLocalDate());
    return totalExpenseQuery;
  }

  @Override
  public List<TotalExpenseByCategoryCommand> getTotalExpenseGroupByCategory(Long householdId,
      LocalDate startTime, LocalDate endTime) {

    return expenseQueryRepository.getTotalExpenseGroupByCategory(householdId, startTime, endTime);
  }

  @Override
  public List<TotalExpenseByCategoryCommand> getTotalUserExpenseGroupByCategory(Long userId, LocalDate startTime,
                                                                                LocalDate endTime) {
    return expenseQueryRepository.getUserTotalExpenseGroupByCategory(userId, startTime, endTime);
  }

  @Override
  public TotalExpenseCommand getUserSendMoney(Long userId, LocalDate date) {
    return expenseQueryRepository.getUserTotalExpenseQuery(userId, date);
  }

  @Override
  public int getHouseholdTotalExpense(Long householdId, LocalDateTime startTime, LocalDateTime endTime) {
    return 1;
//    return expenseQueryRepository.getHouseholdTotalExpense(householdId, startTime.toLocalDate(), endTime.toLocalDate());
  }

  @Override
  public BigDecimal getHouseholdTotalExpenseByDate(Long householdId, LocalDateTime startTime, LocalDateTime endTime) {
    return expenseQueryRepository.getHouseholdTotalExpenseBetweenDate(householdId, startTime,
            endTime.plusDays(1).minusSeconds(1));
  }

  @Override
  public BigDecimal getUserTotalExpenseByDate(Long userId, LocalDateTime startTime, LocalDateTime endTime) {
    return expenseQueryRepository.getUserTotalExpense(userId, startTime, endTime);
  }

  @Override
  public List<DailyTotalExpensesCommand> FindDailyTotalExpenses(Long householdId, LocalDate date) {

    return expenseQueryRepository.getDailyTotalExpenseOfHousehold(householdId, date);
  }

  @Override
  public Long saveExpensePort(Expense expense) {
    ExpenseJpaEntity expenseJpaEntity = expenseMapper.mapToJpaEntity(expense);
    Long expenseId = expenseJpaRepository.save(expenseJpaEntity).getExpenseId();
    return expenseId;
  }

  @Override
  public Expense findExpense(Long expenseId) {
    ExpenseJpaEntity expenseJpaEntity = expenseJpaRepository.findById(expenseId)
        .orElseThrow(() -> new ExpenseNotFoundException("존재하지 않는 expenseId " + expenseId + "입니다."));
    return expenseMapper.mapToDomainEntity(expenseJpaEntity);
  }

  @Override
  public List<DailyExpenseCommand> findDailyExpenses(Long householdId, LocalDate date) {
    return expenseQueryRepository.findDailyExpenses(householdId, date);
  }

  @Override
  public List<DailyExpenseCommand> findSearchedExpenses(Long householdId, SearchedCondition condition) {
    return expenseQueryRepository.findSearchedExpenses(householdId, condition);
  }

  @Override
  public BigDecimal findHouseholdTotalExpenses(Long householdId, LocalDate startDate, LocalDate endDate) {
    return expenseQueryRepository.getHouseholdTotalExpense(householdId, startDate, endDate);
  }

  @Override
  public void deleteExpense(Long expenseId) {
    expenseJpaRepository.deleteById(expenseId);
  }
}
