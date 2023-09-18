package com.connect.accountApp.domain.activitynotification.application.port.in.command;

import com.connect.accountApp.domain.activitynotification.application.port.out.command.FindExpenseNotificationCommand;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetExpenseNotificationCommand {

  private String userImageUrl;
  private String expenseCategory;
  private LocalDateTime createdAt;
  private boolean notiIsRead;
  private int expenseAmount;
  private String senderName;

  public GetExpenseNotificationCommand(FindExpenseNotificationCommand command) {
    this.userImageUrl = command.getExpenseCategory().getImgUrl();
    this.expenseCategory = command.getExpenseCategory().getTitle();
    this.createdAt = command.getCreatedAt();
    this.notiIsRead = command.isNotiIsRead();
    this.expenseAmount = command.getExpenseAmount();
    this.senderName = command.getSenderName();
  }
}
