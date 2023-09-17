package com.connect.accountApp.domain.notification.application.port.out.command;

import com.connect.accountApp.domain.expense.domain.model.ExpenseCategory;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class FindExpenseNotificationCommand {

  private ExpenseCategory expenseCategory;
  private LocalDateTime createdAt;
  private boolean notiIsRead;
  private int expenseAmount;
  private String senderName;


  public FindExpenseNotificationCommand(ExpenseCategory expenseCategory,
      LocalDateTime createdAt, boolean notiIsRead, int expenseAmount, String senderName) {

    this.expenseCategory = expenseCategory;
    this.createdAt = createdAt;
    this.notiIsRead = notiIsRead;
    this.expenseAmount = expenseAmount;
    this.senderName = senderName;
  }
}
