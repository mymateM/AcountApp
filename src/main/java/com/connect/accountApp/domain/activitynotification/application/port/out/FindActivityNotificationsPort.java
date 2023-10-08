package com.connect.accountApp.domain.activitynotification.application.port.out;

import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationCommand;
import com.connect.accountApp.domain.activitynotification.application.port.out.command.NotificationCommand;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import java.util.List;

public interface FindActivityNotificationsPort {

  List<NotificationCommand> findActivityNotifications(Long useId);

  ActivityNotification findActivityNotification(Long activityNotificationId);

  List<ActivityNotificationCommand> findActivityNotifications(String userEmail);

}
