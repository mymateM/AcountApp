package com.connect.accountApp.domain.activitynotification.adapter.out.persistence;

import com.connect.accountApp.domain.bill.adapter.out.persistence.BillMapper;
import com.connect.accountApp.domain.expense.adapter.out.persistence.ExpenseMapper;
import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.NotificationJpaEntity;
import com.connect.accountApp.domain.activitynotification.domain.model.Notification;
import com.connect.accountApp.domain.user.adapter.out.persistence.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

  private final BillMapper billMapper;
  private final UserMapper userMapper;

  public Notification mapToDomainEntity(NotificationJpaEntity notificationJpaEntity) {
    return Notification.builder()
        .activityNotificationCategory(notificationJpaEntity.getActivityNotificationCategory())
        .title(notificationJpaEntity.getTitle())
        .message(notificationJpaEntity.getMessage())
        .isRead(notificationJpaEntity.isRead())
        .createdAt(notificationJpaEntity.getCreatedAt())
        .modifiedAt(notificationJpaEntity.getModifiedAt())
        .bill(billMapper.mapToDomainEntity(notificationJpaEntity.getBillJpaEntity()))
        .requester(userMapper.mapToDomainEntity(notificationJpaEntity.getRequesterJpaEntity()))
        .build();
  }

  public NotificationJpaEntity mapToJpaEntity(Notification notification) {
    return NotificationJpaEntity.builder()
        .activityNotificationCategory(notification.getActivityNotificationCategory())
        .title(notification.getTitle())
        .message(notification.getMessage())
        .isRead(notification.getIsRead())
        .createdAt(notification.getCreatedAt())
        .modifiedAt(notification.getModifiedAt())
        .billJpaEntity(billMapper.mapToJpaEntity(notification.getBill()))
        .requesterJpaEntity(userMapper.mapToJpaEntity(notification.getRequester()))
        .build();
  }

}
