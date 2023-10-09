package com.connect.accountApp.domain.activitynotification.application.port.in;

import java.util.List;

public interface UpdateActivityNotificationUseCase {

  void changeIsReadActivityNotificationsToTrue(List<Long> activity_notification_ids);

}
