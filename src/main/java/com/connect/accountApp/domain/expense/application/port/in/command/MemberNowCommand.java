package com.connect.accountApp.domain.expense.application.port.in.command;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNowCommand {

  private Long userId;
  private String userName;
  private Double userByNowBudgetRatio; // 지금까지 쓴 값이 멤버에게 주어진 예산의 할댱량의 몇퍼 ?
  private int userRatio;

  private UserTitle userTitle;

  public MemberNowCommand(TotalExpenseCommand command, int householdBudget) {
    this.userId = command.getUserId();
    this.userName = command.getUserName();
    this.userByNowBudgetRatio = calUserByNowBudgetRatio(command.getUserTotalExpense(),
        householdBudget, command.getUserRatio());
    this.userRatio = command.getUserRatio();

    this.userTitle = userTitle;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  private class UserTitle {
    private String userTitleName;
    private LocalDate userTitleCreatedAt;

    public UserTitle(UserTitle title) {
      this.userTitleName = userTitleName;
      this.userTitleCreatedAt = userTitleCreatedAt;
    }
  }

  private Double calUserByNowBudgetRatio(int userTotalExpense, int householdBudget, int userRatio) {
    double userRatioExpense = householdBudget * (userRatio / 100.0);
    return (userTotalExpense / userRatioExpense) * 100.0; // TODO: 예산보다 더 썼다면?
  }


}
