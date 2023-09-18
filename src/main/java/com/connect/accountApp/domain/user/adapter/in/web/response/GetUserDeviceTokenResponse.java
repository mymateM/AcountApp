package com.connect.accountApp.domain.user.adapter.in.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDeviceTokenResponse {

  @JsonProperty("device_token")
  private String deviceToken;

}
