package com.connect.accountApp.domain.user.application.port.in.command;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.user.application.port.out.command.RoommateSendMoneyCommand;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class SendMoneyCommand {

  private LocalDate yearMonth;
  private UserCommand user;
  private List<RoommateCommand> roommates = new ArrayList<>();

  public SendMoneyCommand(LocalDate yearMonth,
      TotalExpenseCommand userCommand,
      List<RoommateSendMoneyCommand> roommateCommands,
      int householdTotalExpense) {
    this.yearMonth = yearMonth;
    this.user = new UserCommand(userCommand, householdTotalExpense);
    log.info("[SendMoneyConstructor] roommateCommands.size() : {}", roommateCommands.size());
    roommateCommands.forEach(
        command -> roommates.add(new RoommateCommand(command, user.getUserSettlement()))
    );

    log.info("[SendMoneyConstructor] this.roommates.size() : {}", this.roommates.size());
  }

}
