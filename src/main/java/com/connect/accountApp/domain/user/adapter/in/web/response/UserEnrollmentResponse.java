package com.connect.accountApp.domain.user.adapter.in.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEnrollmentResponse {

  @JsonProperty("household_invite_code")
  private String householdInviteCode;

}
