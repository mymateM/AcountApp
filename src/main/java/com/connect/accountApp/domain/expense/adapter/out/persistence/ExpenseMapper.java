package com.connect.accountApp.domain.expense.adapter.out.persistence;

import com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.ExpenseJpaEntity;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.user.adapter.out.persistence.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseMapper {

  private final UserMapper userMapper;

  public Expense mapToDomainEntity(ExpenseJpaEntity expenseJpaEntity) {
    return Expense.builder()
        .expenseId(expenseJpaEntity.getExpenseId())
        .expenseAmount(expenseJpaEntity.getExpenseAmount())
        .expenseDate(expenseJpaEntity.getExpenseDate())
        .expenseContent(expenseJpaEntity.getExpenseContent())
        .expenseMemo(expenseJpaEntity.getExpenseMemo())
        .user(userMapper.mapToDomainEntity(expenseJpaEntity.getUserJpaEntity()))
        .expenseCategory(expenseJpaEntity.getExpenseCategory())
        .build();
  }

  public ExpenseJpaEntity mapToJpaEntity(Expense expense) {
    return ExpenseJpaEntity.builder()
        .expenseId(expense.getExpenseId())
        .expenseAmount(expense.getExpenseAmount())
        .expenseDate(expense.getExpenseDate())
        .expenseContent(expense.getExpenseContent())
        .expenseMemo(expense.getExpenseMemo())
        .userJpaEntity(userMapper.mapToJpaEntity(expense.getUser()))
        .expenseCategory(expense.getExpenseCategory())
        .build();

  }

}
