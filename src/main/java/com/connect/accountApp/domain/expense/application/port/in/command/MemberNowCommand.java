package com.connect.accountApp.domain.expense.application.port.in.command;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDateTime;
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

  private String userTitleName;
  private String userTitleImg;
  private int userTitleDays;

  public MemberNowCommand(TotalExpenseWithTitleCommand command, int householdBudget) {
    this.userId = command.getUserId();
    this.userName = command.getUserName();
    this.userByNowBudgetRatio = calUserByNowBudgetRatio(command.getUserTotalExpense(),
        householdBudget, command.getUserRatio());
    this.userRatio = command.getUserRatio();

    this.userTitleName = command.getUserTitleName();
    this.userTitleImg = command.getUserTitleImgUrl();
    this.userTitleDays = getUserTitleDays(command.getCreatedAt());

  }

  private Double calUserByNowBudgetRatio(int userTotalExpense, int householdBudget, int userRatio) {
    double userRatioExpense = householdBudget * (userRatio / 100.0);
    return (userTotalExpense / userRatioExpense) * 100.0; // TODO: 예산보다 더 썼다면?
  }

  private int getUserTitleDays(LocalDateTime createdAt) {
    return Long.valueOf(DAYS.between(createdAt, LocalDateTime.now())).intValue();
  }

}
