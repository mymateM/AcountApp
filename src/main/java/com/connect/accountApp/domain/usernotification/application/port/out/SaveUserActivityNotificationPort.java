package com.connect.accountApp.domain.usernotification.application.port.out;

import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
import java.util.List;

public interface SaveUserActivityNotificationPort {

  void saveUserActivityNotification(UserActivityNotification userActivityNotification);

  void saveUserActivityNotifications(List<UserActivityNotification> userActivityNotifications);
}
