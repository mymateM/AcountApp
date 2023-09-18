package com.connect.accountApp.domain.activitynotification.adapter.in.web.dto;

import com.connect.accountApp.domain.activitynotification.application.port.in.command.GetExpenseNotificationCommand;
import com.connect.accountApp.global.utils.DateTimeUtils;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GetExpenseNotificationResponse {

  List<ExpenseNotification> expense_noti;

  public GetExpenseNotificationResponse(List<GetExpenseNotificationCommand> commands) {
    this.expense_noti = commands.stream().map(ExpenseNotification::new).toList();
  }

  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
  private class ExpenseNotification {

    private String user_image_url;
    private String expense_category;
    private String noti_created_at;
    private boolean noti_is_read;
    private int expense_amount;
    private String sender_name;

    public ExpenseNotification(GetExpenseNotificationCommand command) {
      this.user_image_url = command.getUserImageUrl();
      this.expense_category = command.getExpenseCategory();
      this.noti_created_at = DateTimeUtils.timesAgo(command.getCreatedAt());
      this.noti_is_read = command.isNotiIsRead();
      this.expense_amount = command.getExpenseAmount();
      this.sender_name = command.getSenderName();
    }
  }

}
