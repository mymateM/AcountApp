package com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence;

import com.connect.accountApp.domain.expense.adapter.out.persistence.ExpenseMapper;
import com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence.jpa.model.ExpenseNotificationJpaEntity;
import com.connect.accountApp.domain.expensenotification.domain.model.ExpenseNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseNotificationMapper {

  private final ExpenseMapper expenseMapper;


  public ExpenseNotification mapToDomainEntity(ExpenseNotificationJpaEntity expenseNotificationJpaEntity) {
    return ExpenseNotification.builder()
        .id(expenseNotificationJpaEntity.getId())
        .isRead(expenseNotificationJpaEntity.getIsRead())
        .createdAt(expenseNotificationJpaEntity.getCreatedAt())
        .expense(expenseMapper.mapToDomainEntity(expenseNotificationJpaEntity.getExpenseJpaEntity()))
        .build();
  }

  public ExpenseNotificationJpaEntity mapToJpaEntity(ExpenseNotification expenseNotification) {
    return ExpenseNotificationJpaEntity.builder()
        .id(expenseNotification.getId())
        .isRead(expenseNotification.getIsRead())
        .createdAt(expenseNotification.getCreatedAt())
        .expenseJpaEntity(expenseMapper.mapToJpaEntity(expenseNotification.getExpense()))
        .build();

  }

}
