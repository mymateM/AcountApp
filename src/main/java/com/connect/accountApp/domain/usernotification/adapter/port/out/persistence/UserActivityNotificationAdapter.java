package com.connect.accountApp.domain.usernotification.adapter.port.out.persistence;

import com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model.UserActivityNotificationJpaEntity;
import com.connect.accountApp.domain.usernotification.application.port.out.SaveUserActivityNotificationPort;
import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserActivityNotificationAdapter implements SaveUserActivityNotificationPort {

  private final UserActivityNotificationJpaRepository userActivityNotificationJpaRepository;
  private final UserActivityNotificationMapper userActivityMapper;


  @Override
  public void saveUserActivityNotification(UserActivityNotification userActivityNotification) {
    UserActivityNotificationJpaEntity userActivityNotificationJpaEntity = userActivityMapper.mapToJpaEntity(
        userActivityNotification);

    userActivityNotificationJpaRepository.save(userActivityNotificationJpaEntity);
  }
}
