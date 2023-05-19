package com.connect.accountApp.global.common.adapter.in.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PracticeResponse {

  private Integer inner;

  public PracticeResponse(Integer inner) {
    this.inner = inner;
  }
}
