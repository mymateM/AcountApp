package com.connect.accountApp.global.common.adapter.in.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PracticeResponse {

  private String inner;

  public PracticeResponse(String inner) {
    this.inner = inner;
  }
}
