package com.connect.accountApp.domain.household.application.port.in.command;

import lombok.Getter;

@Getter
public class GetMembersSettlementCommand {

  private Long userId;
  private String userProfileImage;
  private String userNickName;
  private Integer userSettlementRatio;

  public GetMembersSettlementCommand(Long userId, String userProfileImage, String userNickName,
      Integer userSettlementRatio) {
    this.userId = userId;
    this.userProfileImage = userProfileImage;
    this.userNickName = userNickName;
    this.userSettlementRatio = userSettlementRatio;
  }

}
