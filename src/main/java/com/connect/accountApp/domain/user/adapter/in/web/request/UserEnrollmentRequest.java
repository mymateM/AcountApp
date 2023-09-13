package com.connect.accountApp.domain.user.adapter.in.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEnrollmentRequest {

  @JsonProperty("user_nickname")
  private String userNickname;

  @JsonProperty("user_profile_image")
  private String userProfileImage;

  @JsonProperty("user_account")
  private UserAccount userAccount;


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class UserAccount {

    @JsonProperty("account_bank")
    private String accountBank;
    @JsonProperty("account_number")
    private String accountNumber;
  }
}
