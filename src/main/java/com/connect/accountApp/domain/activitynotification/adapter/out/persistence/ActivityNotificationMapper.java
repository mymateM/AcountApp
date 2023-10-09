package com.connect.accountApp.domain.activitynotification.adapter.out.persistence;

import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.ActivityNotificationJpaEntity;
import com.connect.accountApp.domain.bill.adapter.out.persistence.BillMapper;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.user.adapter.out.persistence.UserMapper;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityNotificationMapper {

  private final BillMapper billMapper;
  private final UserMapper userMapper;

  public ActivityNotification mapToDomainEntity(ActivityNotificationJpaEntity activityNotificationJpaEntity) {
    return ActivityNotification.builder()
        .activityNotificationId(activityNotificationJpaEntity.getActivityNotificationId())
        .activityNotificationCategory(activityNotificationJpaEntity.getActivityNotificationCategory())
        .title(activityNotificationJpaEntity.getTitle())
        .message(activityNotificationJpaEntity.getMessage())
        .isRead(activityNotificationJpaEntity.isRead())
        .createdAt(activityNotificationJpaEntity.getCreatedAt())
        .modifiedAt(activityNotificationJpaEntity.getModifiedAt())
        .bill(billMapper.mapToDomainEntity(activityNotificationJpaEntity.getBillJpaEntity()))
        .requester(getUser(activityNotificationJpaEntity.getRequesterJpaEntity()))
        .build();
  }

  public ActivityNotificationJpaEntity mapToJpaEntity(ActivityNotification activityNotification) {
    return ActivityNotificationJpaEntity.builder()
        .activityNotificationId(activityNotification.getActivityNotificationId())
        .activityNotificationCategory(activityNotification.getActivityNotificationCategory())
        .title(activityNotification.getTitle())
        .message(activityNotification.getMessage())
        .isRead(activityNotification.getIsRead())
        .createdAt(activityNotification.getCreatedAt())
        .modifiedAt(activityNotification.getModifiedAt())
        .billJpaEntity(billMapper.mapToJpaEntity(activityNotification.getBill()))
        .requesterJpaEntity(getUseJpaEntity(activityNotification.getRequester()))
        .build();
  }

  private User getUser(UserJpaEntity userJpaEntity) {
    if (userJpaEntity == null || userJpaEntity instanceof HibernateProxy) {
      return null;
    } else {
      return userMapper.mapToDomainEntity(userJpaEntity);
    }

  }
  private UserJpaEntity getUseJpaEntity(User user) {
    if (user == null) {
      return null;
    } else {
      return userMapper.mapToJpaEntity(user);
    }
  }

}
