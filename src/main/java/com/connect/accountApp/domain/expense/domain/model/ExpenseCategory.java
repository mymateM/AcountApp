package com.connect.accountApp.domain.expense.domain.model;

import com.connect.accountApp.global.common.service.EnumMapperType;
import lombok.Getter;

@Getter
public enum ExpenseCategory implements EnumMapperType {

  FOOD("식비", "FOOD.jpg"),
  SHOPPING("쇼핑", "SHOPPING.jpg"),
  TRANSPORT("교통", "TRANSPORT.jpg"),
  MEDICAL("의료", "MEDICAL.jpg"),
  HOUSE_ITEM("생활", "HOUSE_ITEM.jpg"),
  ENTERTAINMENT("오락", "ENTERTAINMENT.jpg"),
  SAVE("저축", "SAVE.jpg"),
  EDUCATION("교육", "EDUCATION.jpg"),
  ETC("기타", "ETC.jpg");

  private String title;
  private String imgUrl;

  ExpenseCategory(String title, String imgUrl) {
    this.title = title;
    this.imgUrl = imgUrl;
  }
}
