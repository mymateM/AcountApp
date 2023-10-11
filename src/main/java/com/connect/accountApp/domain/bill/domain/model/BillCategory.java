package com.connect.accountApp.domain.bill.domain.model;

import com.connect.accountApp.global.common.service.EnumMapperType;
import lombok.Getter;

@Getter
public enum BillCategory implements EnumMapperType {

  WATER("수도", "water.jpg"),
  ELECTRICITY("전기", "electricity.jpg"),
  GAS("가스", "gas.jpg"),
  ETC("기타", "etc.jpg");

  private String title;
  private String imgUrl;

  BillCategory(String title, String imgUrl) {
    this.title = title;
    this.imgUrl = imgUrl;
  }
}
