package com.connect.accountApp.domain.expense.application.port.in.command;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementCommand {

  private LocalDate yearDate;
  private int householdTotalExpense;
  private List<MemberCommand> members = new ArrayList<>();

  public SettlementCommand(LocalDate yearDate, List<TotalExpenseCommand> commands) {
    this.yearDate = yearDate;
    this.householdTotalExpense = getHouseholdTotalExpense(commands);
    addMembers(commands);
  }



  private void addMembers(List<TotalExpenseCommand> commands) {
    commands
        .forEach(
            command -> {
              int realRatioExpense = getRealRatioExpense(command.getUserRatio(),
                  householdTotalExpense);
              int userSettlement = getUserSettlement(realRatioExpense,
                  command.getUserTotalExpense());
              this.members.add(new MemberCommand(command.getUserId(), command.getUserName(), command.getUserTotalExpense(),
                  realRatioExpense, userSettlement));
            }
        );
  }


  private int getHouseholdTotalExpense(List<TotalExpenseCommand> commands) {
    householdTotalExpense = 0;
    commands.forEach(command -> householdTotalExpense += command.getUserTotalExpense());
    return householdTotalExpense;
  }

  private int getRealRatioExpense(int userRatio, int householdTotalExpense) {
    return Double.valueOf(householdTotalExpense * (userRatio/100.0)).intValue();
  }

  private int getUserSettlement(int userRealRatioExpense, int householdTotalExpense) {
    return userRealRatioExpense - householdTotalExpense;
  }

}
