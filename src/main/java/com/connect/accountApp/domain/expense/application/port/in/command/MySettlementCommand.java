package com.connect.accountApp.domain.expense.application.port.in.command;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MySettlementCommand {

  private LocalDate yearDate;
  private int householdTotalExpense;
  private MemberCommand user;


  public MySettlementCommand(LocalDate yearDate, int householdTotalExpense, TotalExpenseCommand command) {
    this.yearDate = yearDate;
    this.householdTotalExpense = householdTotalExpense;
    this.user = createMemberCommand(command, householdTotalExpense);
  }

  private MemberCommand createMemberCommand(TotalExpenseCommand command, int householdTotalExpense) {
    int realRatioExpense = getRealRatioExpense(command.getUserRatio(), householdTotalExpense);
    int userSettlement = getUserSettlement(realRatioExpense, command.getUserTotalExpense());
    return new MemberCommand(command.getUserId(), command.getUserName(), command.getUserTotalExpense(), realRatioExpense, userSettlement, command.getUserRatio());
  }

  private int getRealRatioExpense(int userRatio, int householdTotalExpense) {
    return Double.valueOf(householdTotalExpense * (userRatio/100.0)).intValue();
  }

  private int getUserSettlement(int userRealRatioExpense, int householdTotalExpense) {
    return userRealRatioExpense - householdTotalExpense;
  }

}
