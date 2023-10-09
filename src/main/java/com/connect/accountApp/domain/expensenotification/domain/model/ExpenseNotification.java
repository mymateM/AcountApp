package com.connect.accountApp.domain.expensenotification.domain.model;

import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.user.domain.model.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExpenseNotification {

  private Long id;
  private Boolean isRead;
  private LocalDateTime createdAt;

  private Expense expense;
  private User user;

  @Builder
  public ExpenseNotification(Long id, Boolean isRead, LocalDateTime createdAt,
      Expense expense, User user) {
    this.id = id;
    this.isRead = isRead;
    this.createdAt = createdAt;
    this.expense = expense;
    this.user = user;
  }

  public void changeIsReadToFalse() {
    setRead(false);
  }

  public void changeIsReadToTrue() {
    setRead(true);
  }
  private void setRead(Boolean read) {
    isRead = read;
  }
}
