package com.connect.accountApp.domain.user.application.port.in.command;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCommand {

  private Long userId;
  private String userName;
  private int userSettlement;

  public UserCommand(TotalExpenseCommand command, int householdTotalExpense) {
    this.userId = command.getUserId();
    this.userName = command.getUserName();
    // 정산 비율
    this.userSettlement = calUserSettlement(householdTotalExpense, command.getUserTotalExpense(),
        command.getUserRatio());
  }

  private int calUserSettlement(int householdTotalExpense, int userTotalExpense, int userRatio) {
    // 실제 내야하는 돈
    int userRealRatioExpense = Double.valueOf(householdTotalExpense * (userRatio / 100.0)).intValue();
    // 정산 비용 : 실제 정산해야하는 돈 - 실제 내야하는 돈
    return userRealRatioExpense - userTotalExpense;
  }

}
