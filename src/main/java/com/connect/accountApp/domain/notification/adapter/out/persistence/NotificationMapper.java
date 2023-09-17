package com.connect.accountApp.domain.notification.adapter.out.persistence;

import com.connect.accountApp.domain.bill.adapter.out.persistence.BillMapper;
import com.connect.accountApp.domain.expense.adapter.out.persistence.ExpenseMapper;
import com.connect.accountApp.domain.notification.adapter.out.persistence.jpa.model.NotificationJpaEntity;
import com.connect.accountApp.domain.notification.domain.model.Notification;
import com.connect.accountApp.domain.user.adapter.out.persistence.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

  private final ExpenseMapper expenseMapper;
  private final BillMapper billMapper;
  private final UserMapper userMapper;

  public Notification mapToDomainEntity(NotificationJpaEntity notificationJpaEntity) {
    return Notification.builder()
        .notiId(notificationJpaEntity.getNotiId())
        .notiCategory(notificationJpaEntity.getNotiCategory())
        .notiContent(notificationJpaEntity.getNotiContent())
        .notiIsRead(notificationJpaEntity.isNotiIsRead())
        .notiCreatedAt(notificationJpaEntity.getNotiCreatedAt())
        .expense(expenseMapper.mapToDomainEntity(notificationJpaEntity.getExpenseJpaEntity()))
        .bill(billMapper.mapToDomainEntity(notificationJpaEntity.getBillJpaEntity()))
        .senderName(notificationJpaEntity.getSenderName())
        .build();
  }

  public NotificationJpaEntity mapToJpaEntity(Notification notification) {
    return NotificationJpaEntity.builder()
        .notiId(notification.getNotiId())
        .notiCategory(notification.getNotiCategory())
        .notiContent(notification.getNotiContent())
        .notiIsRead(notification.isNotiIsRead())
        .notiCreatedAt(notification.getNotiCreatedAt())
        .expenseJpaEntity(expenseMapper.mapToJpaEntity(notification.getExpense()))
        .billJpaEntity(billMapper.mapToJpaEntity(notification.getBill()))
        .senderName(notification.getSenderName())
        .build();
  }

}
