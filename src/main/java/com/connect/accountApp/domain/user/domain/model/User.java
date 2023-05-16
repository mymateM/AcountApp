package com.connect.accountApp.domain.user.domain.model;

import com.connect.accountApp.domain.household.domain.model.Household;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  private Long userId;
  private String userName;
  private String userImgUrl;

  private String userAccount;
  private String userAccountBank;

  private boolean userAccept;
  private int userRatio;

  private Household household;

  @Builder
  public User(Long userId, String userName, String userImgUrl, String userAccount,
      String userAccountBank, boolean userAccept, int userRatio,
      Household household) {
    this.userId = userId;
    this.userName = userName;
    this.userImgUrl = userImgUrl;
    this.userAccount = userAccount;
    this.userAccountBank = userAccountBank;
    this.userAccept = userAccept;
    this.userRatio = userRatio;
    this.household = household;
  }
}
