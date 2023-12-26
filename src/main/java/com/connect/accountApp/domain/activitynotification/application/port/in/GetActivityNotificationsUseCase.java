package com.connect.accountApp.domain.activitynotification.application.port.in;

import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationCommand;
import java.util.List;

public interface GetActivityNotificationsUseCase {

  List<ActivityNotificationCommand> getActivityNotifications(String userEmail);


}
