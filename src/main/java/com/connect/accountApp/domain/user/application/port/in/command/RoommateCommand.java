package com.connect.accountApp.domain.user.application.port.in.command;

import com.connect.accountApp.domain.user.application.port.out.command.RoommateSendMoneyCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoommateCommand {

  private Long userId;
  private String userName;
  private int userRatio;
  private int userSettlementRatio;
  private String userAccountBank;
  private String userAccount;

  public RoommateCommand(RoommateSendMoneyCommand command, int userSettlement) {
    this.userId = command.getUserId();
    this.userName = command.getUserName();
    this.userRatio = command.getUserRatio();
    this.userSettlementRatio = calUserSettlementRatio(command.getUserRatio(), userSettlement);
    this.userAccountBank = command.getUserAccountBank();
    this.userAccount = command.getUserAccount();
  }

  private int calUserSettlementRatio(int userRatio, int userSettlement) {
    return Double.valueOf(userSettlement * (userRatio / 100.0)).intValue();

  }

}
