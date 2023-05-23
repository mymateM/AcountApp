package com.connect.accountApp.global.common.service;

import lombok.Getter;

@Getter
public class EnumMapperValue {

  private String title;
  private String imgUrl;

  public EnumMapperValue(EnumMapperType enumMapperType) {
    this.title = enumMapperType.getTitle();
    this.imgUrl = enumMapperType.getImgUrl();
  }

  @Override
  public String toString() {
    return "EnumMapperValue{" +
        "title='" + title + '\'' +
        ", imgUrl='" + imgUrl + '\'' +
        '}';
  }
}
