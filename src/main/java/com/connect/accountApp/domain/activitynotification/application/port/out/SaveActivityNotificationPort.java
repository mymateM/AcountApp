package com.connect.accountApp.domain.activitynotification.application.port.out;

import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;

public interface SaveActivityNotificationPort {

  void saveActivityNotification(ActivityNotification activityNotification);

}
