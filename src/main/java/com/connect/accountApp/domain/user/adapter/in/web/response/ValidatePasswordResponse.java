package com.connect.accountApp.domain.user.adapter.in.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidatePasswordResponse {

  @JsonProperty("is_validated_password")
  private Boolean isValidatedPassword;

  public ValidatePasswordResponse(Boolean isValidatedPassword) {
    this.isValidatedPassword = isValidatedPassword;
  }
}