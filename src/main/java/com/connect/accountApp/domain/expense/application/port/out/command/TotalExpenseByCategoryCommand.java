package com.connect.accountApp.domain.expense.application.port.out.command;

import com.connect.accountApp.domain.expense.domain.model.ExpenseCategory;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TotalExpenseByCategoryCommand {

  private ExpenseCategory expenseCategory;
  private BigDecimal totalExpenseAmount;

  public TotalExpenseByCategoryCommand(
      ExpenseCategory expenseCategory, BigDecimal totalExpenseAmount) {
    this.expenseCategory = expenseCategory;
    this.totalExpenseAmount = totalExpenseAmount;
  }
}
