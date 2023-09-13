package com.connect.accountApp.domain.user.domain.model;

import com.connect.accountApp.domain.household.domain.model.Household;
import java.util.Arrays;
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

  private String deviceToken;

  @Builder
  public User(Long userId, String userEmail, String userPassword,
      String userNickname, String userImgUrl, Role role,
      String userAccount, Bank userAccountBank, boolean userAccept, int userRatio,
      Household household, String deviceToken) {
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
    this.deviceToken = deviceToken;
  }

  public void changeDeviceToken(String deviceToken) {
    setDeviceToken(deviceToken);
  }

  public void UpdateUserAtOnBoarding(String userNickname, String bankName, String account, String userProfileImg) {
    setUserNickname(userNickname);
    setUserAccountBank(Bank.valueOf(bankName));
    setUserAccount(account);
    setUserImgUrl(userProfileImg);
  }


  private void setUserNickname(String userNickname) {
    this.userNickname = userNickname;
  }

  private void setUserImgUrl(String userImgUrl) {
    this.userImgUrl = userImgUrl;
  }

  private void setUserAccount(String userAccount) {
    this.userAccount = userAccount;
  }

  private void setUserAccountBank(Bank userAccountBank) {
    this.userAccountBank = userAccountBank;
  }

  private void setUserAccept(boolean userAccept) {
    this.userAccept = userAccept;
  }

  private void setUserRatio(int userRatio) {
    this.userRatio = userRatio;
  }

  private void setHousehold(Household household) {
    this.household = household;
  }

  private void setRole(Role role) {
    this.role = role;
  }

  private void setDeviceToken(String deviceToken) {
    this.deviceToken = deviceToken;
  }
}
