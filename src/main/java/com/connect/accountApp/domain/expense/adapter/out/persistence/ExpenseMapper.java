package com.connect.accountApp.domain.expense.adapter.out.persistence;

import com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.ExpenseJpaEntity;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.user.adapter.out.persistence.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseMapper {

  public Expense mapToDomainEntity(ExpenseJpaEntity expenseJpaEntity) {
    return Expense.builder()
        .expenseId(expenseJpaEntity.getExpenseId())
        .expenseAmount(expenseJpaEntity.getExpenseAmount())
        .expenseDate(expenseJpaEntity.getExpenseDate())
        .expenseStore(expenseJpaEntity.getExpenseStore())
        .expenseMemo(expenseJpaEntity.getExpenseMemo())
        .expenseCategory(expenseJpaEntity.getExpenseCategory())
        .build();
  }

  public ExpenseJpaEntity mapToJpaEntity(Expense expense) {
    return ExpenseJpaEntity.builder()
        .expenseId(expense.getExpenseId())
        .expenseAmount(expense.getExpenseAmount())
        .expenseDate(expense.getExpenseDate())
        .expenseStore(expense.getExpenseStore())
        .expenseMemo(expense.getExpenseMemo())
        .expenseCategory(expense.getExpenseCategory())
        .build();

  }

}
