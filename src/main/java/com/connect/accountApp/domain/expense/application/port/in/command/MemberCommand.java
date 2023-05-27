package com.connect.accountApp.domain.expense.application.port.in.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCommand {

  private Long userId;
  private String userName;
  private int userTotalExpense;
  private int userRatioExpense;
  private int userSettlement;
  private int userRatio;


  public MemberCommand(Long userId, String userName, int userTotalExpense, int userRatioExpense,
      int userSettlement) {
    this.userId = userId;
    this.userName = userName;
    this.userTotalExpense = userTotalExpense;
    this.userRatioExpense = userRatioExpense;
    this.userSettlement = userSettlement;
  }

  public MemberCommand(Long userId, String userName, int userTotalExpense, int userRatioExpense, int userSettlement, int userRatio) {
    this.userId = userId;
    this.userName = userName;
    this.userTotalExpense = userTotalExpense;
    this.userRatioExpense = userRatioExpense;
    this.userSettlement = userSettlement;
    this.userRatio = userRatio;
  }
}
