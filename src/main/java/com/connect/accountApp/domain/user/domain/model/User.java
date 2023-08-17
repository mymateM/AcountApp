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
  private String userEmail;
  private String userPassword;
  private String userNickname;
  private String userImgUrl;

  private String userAccount;
  private Bank userAccountBank;

  private boolean userAccept;
  private int userRatio;

  private Household household;

  private Role role;

  @Builder
  public User(Long userId, String userEmail, String userPassword,
      String userNickname, String userImgUrl, Role role,
      String userAccount, Bank userAccountBank, boolean userAccept, int userRatio,
      Household household) {
    this.userId = userId;
    this.userEmail = userEmail;
    this.userPassword = userPassword;
    this.userNickname = userNickname;
    this.userImgUrl = userImgUrl;
    this.role = role;
    this.userAccount = userAccount;
    this.userAccountBank = userAccountBank;
    this.userAccept = userAccept;
    this.userRatio = userRatio;
    this.household = household;
  }
}
