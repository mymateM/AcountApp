package com.connect.accountApp.domain.usernotification.adapter.port.out.persistence;

import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.ActivityNotificationMapper;
import com.connect.accountApp.domain.user.adapter.out.persistence.UserMapper;
import com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model.UserActivityNotificationJpaEntity;
import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserActivityNotificationMapper {

  private final UserMapper userMapper;
  private final ActivityNotificationMapper activityNotificationMapper;

  public UserActivityNotification mapToDomainEntity(
      UserActivityNotificationJpaEntity userActivityNotificationJpaEntity) {

    return UserActivityNotification.builder()
        .userNotiId(userActivityNotificationJpaEntity.getUserNotiId())
        .user(userMapper.mapToDomainEntity(userActivityNotificationJpaEntity.getUserJpaEntity()))
        .activityNotification(activityNotificationMapper.mapToDomainEntity(
            userActivityNotificationJpaEntity.getActivityNotificationJpaEntity()))
        .build();
  }

  public UserActivityNotificationJpaEntity mapToJpaEntity(UserActivityNotification userActivityNotification) {

    return UserActivityNotificationJpaEntity.builder()
        .userNotiId(userActivityNotification.getUserNotiId())
        .userJpaEntity(userMapper.mapToJpaEntity(userActivityNotification.getUser()))
        .activityNotificationJpaEntity(activityNotificationMapper.mapToJpaEntity(userActivityNotification.getActivityNotification()))
        .build();


  }

}
