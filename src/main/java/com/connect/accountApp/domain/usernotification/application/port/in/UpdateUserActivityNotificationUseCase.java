package com.connect.accountApp.domain.usernotification.application.port.in;

import java.util.List;

public interface UpdateUserActivityNotificationUseCase {

  void changeIsReadUserActivityNotificationsToTrue(List<Long> activity_notification_ids);

}
