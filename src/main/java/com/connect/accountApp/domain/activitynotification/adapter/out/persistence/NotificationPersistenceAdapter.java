package com.connect.accountApp.domain.activitynotification.adapter.out.persistence;

import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.ActivityNotificationJpaRepository;
import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.ActivityNotificationJpaEntity;
import com.connect.accountApp.domain.activitynotification.application.port.out.FindActivityNotificationsPort;
import com.connect.accountApp.domain.activitynotification.application.port.out.FindExpenseNotificationPort;
import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.application.port.out.command.FindExpenseNotificationCommand;
import com.connect.accountApp.domain.activitynotification.application.port.out.command.NotificationCommand;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.activitynotification.exception.ActivityNotificationNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements FindActivityNotificationsPort,
    FindExpenseNotificationPort, SaveActivityNotificationPort {

  private final NotificationQueryRepository notificationQueryRepository;
  private final ActivityNotificationJpaRepository activityNotificationJpaRepository;
  private final ActivityNotificationMapper activityNotificationMapper;

  @Override
  public List<NotificationCommand> findActivityNotifications(Long useId) {
    return notificationQueryRepository.findActivityNotifications(useId);
  }

  @Override
  public ActivityNotification findActivityNotification(Long activityNotificationId) {
    ActivityNotificationJpaEntity activityNotificationJpaEntity = activityNotificationJpaRepository.findById(
        activityNotificationId).orElseThrow(
        () -> new ActivityNotificationNotFoundException(
            "[activity notification] 존재하지 않는 id : " + activityNotificationId + " 입니다.")
    );

    return activityNotificationMapper.mapToDomainEntity(activityNotificationJpaEntity);
  }

  @Override
  public List<FindExpenseNotificationCommand> findExpenseNotification(Long userId) {

    return notificationQueryRepository.findExpenseNotifications(userId);
  }

  @Override
  public Long saveActivityNotification(ActivityNotification activityNotification) {

    ActivityNotificationJpaEntity activityNotificationJpaEntity = activityNotificationMapper.mapToJpaEntity(
        activityNotification);
    return activityNotificationJpaRepository.save(activityNotificationJpaEntity)
        .getActivityNotificationId();

  }
}
