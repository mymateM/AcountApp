package com.connect.accountApp.domain.expensenotification.domain.model;

import com.connect.accountApp.domain.expense.domain.model.Expense;
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

  @Builder
  public ExpenseNotification(Long id, Boolean isRead, LocalDateTime createdAt,
      Expense expense) {
    this.id = id;
    this.isRead = isRead;
    this.createdAt = createdAt;
    this.expense = expense;
  }
}
