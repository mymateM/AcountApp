package com.connect.accountApp.domain.expense.application.port.in.command;

import com.connect.accountApp.domain.expense.domain.model.ExpenseCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyExpenseCommand {

  private Long expenseId;
  private BigDecimal expenseAmount;
  private String expenseStore;
  private ExpenseCategory expenseCategory;
  private LocalDate expenseDate;

  public DailyExpenseCommand(Long expenseId, BigDecimal expenseAmount, String expenseStore,
                             ExpenseCategory expenseCategory, LocalDate expenseDate) {
    this.expenseId = expenseId;
    this.expenseAmount = expenseAmount;
    this.expenseStore = expenseStore;
    this.expenseCategory = expenseCategory;
    this.expenseDate = expenseDate;
  }

}
