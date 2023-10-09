package com.connect.accountApp.domain.activitynotification.application.service;

import com.connect.accountApp.domain.activitynotification.application.port.in.UpdateActivityNotificationUseCase;
import com.connect.accountApp.domain.activitynotification.application.port.out.FindActivityNotificationsPort;
import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateActivityNotificationService implements UpdateActivityNotificationUseCase {

  private final FindActivityNotificationsPort findActivityNotificationsPort;
  private final SaveActivityNotificationPort saveActivityNotificationPort;

  @Override
  public void changeIsReadActivityNotificationsToTrue(List<Long> activity_notification_ids) {
    List<UserActivityNotification> userActivityNotifications =
        findActivityNotificationsPort.findActivityNotification(activity_notification_ids);
    userActivityNotifications.forEach(UserActivityNotification::changeIsReadToTrue);
    saveActivityNotificationPort.saveUserActivityNotifications(userActivityNotifications);
  }
}
