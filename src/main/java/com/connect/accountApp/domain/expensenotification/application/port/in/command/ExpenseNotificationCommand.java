package com.connect.accountApp.domain.expensenotification.application.port.in.command;

import com.connect.accountApp.domain.expense.domain.model.ExpenseCategory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExpenseNotificationCommand {

  private Long expenseNotificationId;
  private Long expenseId;
  private String expenseCategoryImageUrl;
  private String expenseCategoryTitle;
  private LocalDateTime createdAt;
  private Boolean isRead;
  private BigDecimal expenseAmount;
  private String spenderName;

  public ExpenseNotificationCommand(Long expenseNotificationId, Long expenseId,
      ExpenseCategory expenseCategory, LocalDateTime createdAt, Boolean isRead,
      BigDecimal expenseAmount) {
    this.expenseNotificationId = expenseNotificationId;
    this.expenseId = expenseId;
    this.expenseCategoryImageUrl = expenseCategory.getImgUrl();
    this.expenseCategoryTitle = expenseCategory.getTitle();
    this.createdAt = createdAt;
    this.isRead = isRead;
    this.expenseAmount = expenseAmount;
//    this.spenderName = spenderName;
  }

  public void setSpenderName(String spenderName) {
    this.spenderName = spenderName;
  }
}
