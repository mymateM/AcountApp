package com.connect.accountApp.domain.title.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Title {

  private Long titleId;
  private String titleName;
  private String titleImg;

  @Builder
  public Title(Long titleId, String titleName, String titleImg) {
    this.titleId = titleId;
    this.titleName = titleName;
    this.titleImg = titleImg;
  }
}
