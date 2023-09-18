package com.connect.accountApp.domain.expense.application.port.out.command;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyTotalExpensesCommand {

  private Integer expenseDayOfMonth;
  private BigDecimal dailyTotalExpense;

  public DailyTotalExpensesCommand(Integer expenseDayOfMonth, BigDecimal dailyTotalExpense) {
    this.expenseDayOfMonth = expenseDayOfMonth;
    this.dailyTotalExpense = dailyTotalExpense;
  }
}
