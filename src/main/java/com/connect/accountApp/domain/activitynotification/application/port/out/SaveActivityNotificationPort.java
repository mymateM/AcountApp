package com.connect.accountApp.domain.activitynotification.application.port.out;

import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
import java.util.List;

public interface SaveActivityNotificationPort {

  Long saveActivityNotification(ActivityNotification activityNotification);

  void saveUserActivityNotifications(List<UserActivityNotification> userActivityNotifications);

}
