package com.connect.accountApp.domain.notification.domain.model;

import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

  private Long notiId;
  private NotiCategory notiCategory;
  private String notiContent;
  private boolean notiIsRead;

  private Expense expense;
  private Bill bill;

  @Builder
  public Notification(Long notiId,
      NotiCategory notiCategory, String notiContent, boolean notiIsRead,
      Expense expense, Bill bill) {
    this.notiId = notiId;
    this.notiCategory = notiCategory;
    this.notiContent = notiContent;
    this.notiIsRead = notiIsRead;
    this.expense = expense;
    this.bill = bill;
  }
}
