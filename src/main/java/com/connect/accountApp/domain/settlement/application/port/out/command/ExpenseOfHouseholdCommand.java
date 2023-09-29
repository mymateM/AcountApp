package com.connect.accountApp.domain.settlement.application.port.out.command;

import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
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

  @Getter
  @NoArgsConstructor
  @Setter
  public static class ExpenseRatioOfUser {
    private Long userId;
    private Integer userExpenseRatio;

    public ExpenseRatioOfUser(Long userId, Integer userExpenseRatio) {
      this.userId = userId;
      this.userExpenseRatio = userExpenseRatio;
    }

  }


}
