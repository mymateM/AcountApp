package com.connect.accountApp.domain.activitynotification.adapter.out.persistence;

import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.ActivityNotificationJpaRepository;
import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.ActivityNotificationJpaEntity;
import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationCommand;
import com.connect.accountApp.domain.activitynotification.application.port.out.FindActivityNotificationsPort;
import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.activitynotification.exception.ActivityNotificationNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityNotificationPersistenceAdapter implements FindActivityNotificationsPort, SaveActivityNotificationPort {

  private final NotificationQueryRepository notificationQueryRepository;
  private final ActivityNotificationJpaRepository activityNotificationJpaRepository;
  private final ActivityNotificationMapper activityNotificationMapper;

  @Override
  public ActivityNotification findUserActivityNotification(Long activityNotificationId) {
    ActivityNotificationJpaEntity activityNotificationJpaEntity = activityNotificationJpaRepository.findById(
        activityNotificationId).orElseThrow(
        () -> new ActivityNotificationNotFoundException(
            "[activity notification] 존재하지 않는 id : " + activityNotificationId + " 입니다.")
    );

    return activityNotificationMapper.mapToDomainEntity(activityNotificationJpaEntity);
  }

  @Override
  public List<ActivityNotification> findUserActivityNotificationByBill(List<Long> billIds) {
    List<ActivityNotificationJpaEntity> ActivityJpaEntities =
            notificationQueryRepository.findActivityNotificationByBill(billIds);
    return ActivityJpaEntities.stream().map(activityNotificationMapper::mapToDomainEntity).toList();
  }

  @Override
  public List<ActivityNotificationCommand> findActivityNotifications(String userEmail) {
    return notificationQueryRepository.findActivityNotifications(userEmail);
  }

  @Override
  public ActivityNotification findActivityNotification(Long activityNotificationId) {
    ActivityNotificationJpaEntity activityNotificationJpaEntity = activityNotificationJpaRepository
        .findById(activityNotificationId)
        .orElseThrow(
            () -> new ActivityNotificationNotFoundException(
                "[activityNotificationId] " + activityNotificationId + "에 해당하는 객체가 존재하지 않습니다.")
        );

    return activityNotificationMapper.mapToDomainEntity(activityNotificationJpaEntity);
  }

  @Override
  public Long saveActivityNotification(ActivityNotification activityNotification) {

    ActivityNotificationJpaEntity activityNotificationJpaEntity = activityNotificationMapper.mapToJpaEntity(
        activityNotification);
    return activityNotificationJpaRepository.save(activityNotificationJpaEntity)
        .getActivityNotificationId();

  }

  @Override
  public void saveAllActivityNotification(List<ActivityNotification> activityNotifications) {
    List<ActivityNotificationJpaEntity> activityNotificationJpaEntities = activityNotifications.stream()
            .map(activityNotificationMapper::mapToJpaEntity).toList();
    activityNotificationJpaRepository.saveAll(activityNotificationJpaEntities);
  }

}
