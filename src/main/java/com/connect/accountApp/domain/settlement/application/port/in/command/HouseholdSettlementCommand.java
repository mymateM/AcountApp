package com.connect.accountApp.domain.settlement.application.port.in.command;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseholdSettlementCommand {

  private UserSettlementCommand userSettlementCommand;
  private List<RoommateSettlementCommand> roommateSettlementCommands;

  public HouseholdSettlementCommand(
      UserSettlementCommand userSettlementCommand,
      List<RoommateSettlementCommand> roommateSettlementCommands) {
    this.userSettlementCommand = userSettlementCommand;
    this.roommateSettlementCommands = roommateSettlementCommands;
  }
}
