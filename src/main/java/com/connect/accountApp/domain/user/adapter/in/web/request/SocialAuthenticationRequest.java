package com.connect.accountApp.domain.user.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialAuthenticationRequest {

  private String socialAuthType;
  private String socialAccessToken;

}
