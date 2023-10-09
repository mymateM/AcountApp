package com.connect.accountApp.domain.expensenotification.adapter.port.in.web.response;

import com.connect.accountApp.domain.expensenotification.application.port.in.command.ExpenseNotificationCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetExpenseNotificationsResponse {

  @JsonProperty("notification_expenses")
  private List<ExpenseNotificationResponse> notificationResponses;

  public GetExpenseNotificationsResponse(List<ExpenseNotificationCommand> commands) {
    this.notificationResponses = commands.stream().map(ExpenseNotificationResponse::new).toList();
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class ExpenseNotificationResponse {

    @JsonProperty("expense_notification_id")
    private Long expenseNotificationId;
    @JsonProperty("expense_category_image_url")
    private String expenseCategoryImageUrl;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("is_read")
    private Boolean isRead;
    @JsonProperty("expense_amount")
    private BigDecimal expenseAmount;
    @JsonProperty("spender_name")
    private String spenderName;

    public ExpenseNotificationResponse(ExpenseNotificationCommand command) {
      this.expenseNotificationId = command.getExpenseNotificationId();
      this.expenseCategoryImageUrl = command.getExpenseCategoryImageUrl();
      this.createdAt = command.getCreatedAt();
      this.isRead = command.getIsRead();
      this.expenseAmount = command.getExpenseAmount();
      this.spenderName = command.getSpenderName();
    }
  }



}
