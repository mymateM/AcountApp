package com.connect.accountApp.domain.expense.domain.model;

import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Expense {

  private Long expenseId;
  private BigDecimal expenseAmount;
  private LocalDateTime expenseDate;
  private String expenseContent;
  private String expenseMemo;
  private User user;
  private ExpenseCategory expenseCategory;

  @Builder
  public Expense(Long expenseId, BigDecimal expenseAmount, LocalDateTime expenseDate,
      String expenseContent, String expenseMemo,
      User user, ExpenseCategory expenseCategory) {
    this.expenseId = expenseId;
    this.expenseAmount = expenseAmount;
    this.expenseDate = expenseDate;
    this.expenseContent = expenseContent;
    this.expenseMemo = expenseMemo;
    this.user = user;
    this.expenseCategory = expenseCategory;
  }
}
