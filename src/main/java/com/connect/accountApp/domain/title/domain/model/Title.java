package com.connect.accountApp.domain.title.domain.model;

import lombok.Getter;

@Getter
public enum Title {

  NO_SPEND(101, "무소비", "image.jpg"),
  GOOD_MANAGEMENT(102, "우리집 관리 왕", "image.jpg"),
  OVER_SPEND(103, "빨간 불! 지출 위험", "image.jpg");

  private final Integer titleCode;
  private final String titleName;
  private final String titleImage;

  Title(int titleCode, String titleName, String titleImage) {
    this.titleCode = titleCode;
    this.titleName = titleName;
    this.titleImage = titleImage;
  }
}
