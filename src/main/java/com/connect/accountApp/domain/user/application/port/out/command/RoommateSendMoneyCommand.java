package com.connect.accountApp.domain.user.application.port.out.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoommateSendMoneyCommand {

  private Long userId;
  private String userName;
  private int userRatio;
  private String userAccountBank;
  private String userAccount;

  public RoommateSendMoneyCommand(Long userId, String userName, int userRatio,
      String userAccountBank, String userAccount) {
    this.userId = userId;
    this.userName = userName;
    this.userRatio = userRatio;
    this.userAccountBank = userAccountBank;
    this.userAccount = userAccount;
  }
}
