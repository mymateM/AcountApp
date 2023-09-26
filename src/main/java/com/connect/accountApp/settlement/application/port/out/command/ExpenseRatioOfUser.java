package com.connect.accountApp.settlement.application.port.out.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExpenseRatioOfUser {
  private Long userId;
  private Integer userExpenseRatio;

  public ExpenseRatioOfUser(Long userId, Integer userExpenseRatio) {
    this.userId = userId;
    this.userExpenseRatio = userExpenseRatio;
  }

}
