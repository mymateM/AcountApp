package com.connect.accountApp.domain.notification.domain.model;

import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.user.domain.model.User;
import java.time.LocalDateTime;
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
  private LocalDateTime notiCreatedAt;

  private Expense expense;
  private Bill bill;
  private String senderName;

  @Builder
  public Notification(Long notiId,
      NotiCategory notiCategory, String notiContent, boolean notiIsRead, LocalDateTime notiCreatedAt,
      Expense expense, Bill bill, String senderName) {
    this.notiId = notiId;
    this.notiCategory = notiCategory;
    this.notiContent = notiContent;
    this.notiIsRead = notiIsRead;
    this.expense = expense;
    this.bill = bill;
    this.senderName = senderName;
  }
}
