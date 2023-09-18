package com.connect.accountApp.domain.usernotification.application.port.out;

import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;

public interface SaveUserActivityNotificationPort {

  void saveUserActivityNotification(UserActivityNotification userActivityNotification);

}
