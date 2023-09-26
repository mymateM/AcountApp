package com.connect.accountApp.settlement.application.port.out.command;

import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExpenseOfHouseholdCommand {

  private Long expenseId;
  private BigDecimal expenseAmount;
  private List<ExpenseRatioOfUser> expenseRatioOfUsers;

  public ExpenseOfHouseholdCommand(Long expenseId, BigDecimal expenseAmount,
      List<ExpenseRatioOfUser> expenseRatioOfUsers) {
    this.expenseId = expenseId;
    this.expenseAmount = expenseAmount;
    this.expenseRatioOfUsers = expenseRatioOfUsers;
  }


}
