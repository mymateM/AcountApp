package com.connect.accountApp.domain.expense.domain.model;

import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDate;
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
  private LocalDate expenseDate;
  private String expenseStore;
  private String expenseMemo;
  private ExpenseCategory expenseCategory;

  @Builder
  public Expense(Long expenseId, BigDecimal expenseAmount, LocalDate expenseDate,
      String expenseStore, String expenseMemo,
      User user, ExpenseCategory expenseCategory) {
    this.expenseId = expenseId;
    this.expenseAmount = expenseAmount;
    this.expenseDate = expenseDate;
    this.expenseStore = expenseStore;
    this.expenseMemo = expenseMemo;
    this.expenseCategory = expenseCategory;
  }
}
