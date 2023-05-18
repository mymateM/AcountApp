package com.connect.accountApp.domain.expense.application.port.out.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TotalExpenseCommand {

  private Long userId;
  private String userName;
  private int userTotalExpense;
  private int userRatio;

  public TotalExpenseCommand(Long userId, String userName, int userTotalExpense, int userRatio) {
    this.userId = userId;
    this.userName = userName;
    this.userTotalExpense = userTotalExpense;
    this.userRatio = userRatio;
  }
}
