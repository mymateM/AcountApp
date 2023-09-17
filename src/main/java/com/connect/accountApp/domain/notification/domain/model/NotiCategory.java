package com.connect.accountApp.domain.notification.domain.model;

import com.connect.accountApp.global.common.service.EnumMapperType;
import lombok.Getter;

@Getter
public enum NotiCategory implements EnumMapperType {

  REQUEST_SETTLEMENT("정산 디데이","request_settlement.jpg"),
  REPORT_MONTH_SETTLEMENT("월별 리포트","report_month_settlement.jpg"),
  STORE_BILL("고지서 보관","store_bill.jpg"),
  WARN_OVER_BUDGET("예산 초과 경고","warn_over_budget.jpg"),
  ALARM_EXPENSE("지출 알람","alarm_expense.jpg");


  private String title;
  private String imgUrl;
//  private Boolean IsActivity;

  NotiCategory(String title, String imgUrl) {
    this.title = title;
    this.imgUrl = imgUrl;
  }
}
