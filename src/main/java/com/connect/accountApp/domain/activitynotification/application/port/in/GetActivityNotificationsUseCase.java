package com.connect.accountApp.domain.activitynotification.application.port.in;

import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationCommand;
import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationsCommand;
import java.util.List;

public interface GetActivityNotificationsUseCase {

  List<ActivityNotificationsCommand> getActivityNotification(Long userId);

  List<ActivityNotificationCommand> getActivityNotifications(String userEmail);


}
