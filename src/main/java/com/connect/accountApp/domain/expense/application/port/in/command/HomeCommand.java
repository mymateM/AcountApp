package com.connect.accountApp.domain.expense.application.port.in.command;

import com.connect.accountApp.domain.household.application.port.in.command.HouseholdNowCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomeCommand {

  private MembersNowCommand membersNowCommand;
  private HouseholdNowCommand householdNowCommand;

  public HomeCommand(
      MembersNowCommand membersNowCommand,
      HouseholdNowCommand householdNowCommand) {
    this.membersNowCommand = membersNowCommand;
    this.householdNowCommand = householdNowCommand;
  }
}
