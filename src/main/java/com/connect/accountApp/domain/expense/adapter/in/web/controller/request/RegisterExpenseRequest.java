package com.connect.accountApp.domain.expense.adapter.in.web.controller.request;

import com.connect.accountApp.domain.expense.domain.model.ExpenseCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterExpenseRequest {

  private LocalDate expenseDate;
  private BigDecimal expenseAmount;
  private List<Long> settlementSubjectIds;
  private String expenseStore;
  private ExpenseCategory expenseCategory;
  private String expenseMemo;

  @Builder
  public RegisterExpenseRequest(LocalDate expenseDate, BigDecimal expenseAmount,
      List<Long> settlementSubjectIds, String expenseStore,
      ExpenseCategory expenseCategory, String expenseMemo) {
    this.expenseDate = expenseDate;
    this.expenseAmount = expenseAmount;
    this.settlementSubjectIds = settlementSubjectIds;
    this.expenseStore = expenseStore;
    this.expenseCategory = expenseCategory;
    this.expenseMemo = expenseMemo;
  }
}
