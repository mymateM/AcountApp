package com.connect.accountApp.domain.activitynotification.application.service;

import com.connect.accountApp.domain.activitynotification.application.port.in.UpdateActivityNotificationUseCase;
import com.connect.accountApp.domain.usernotification.application.port.out.FindUserActivityNotificationsPort;
import com.connect.accountApp.domain.usernotification.application.port.out.SaveUserActivityNotificationPort;
import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateActivityNotificationService implements UpdateActivityNotificationUseCase {

  private final FindUserActivityNotificationsPort findUserActivityNotificationsPort;
  private final SaveUserActivityNotificationPort saveUserActivityNotificationPort;

  @Override
  public void changeIsReadActivityNotificationsToTrue(List<Long> activity_notification_ids) {
    List<UserActivityNotification> userActivityNotifications =
        findUserActivityNotificationsPort.findUserActivityNotification(activity_notification_ids);
    userActivityNotifications.forEach(UserActivityNotification::changeIsReadToTrue);
    saveUserActivityNotificationPort.saveUserActivityNotifications(userActivityNotifications);
  }
}
