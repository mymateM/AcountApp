package com.connect.accountApp.domain.activitynotification.application.port.out;

import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationCommand;
import com.connect.accountApp.domain.activitynotification.application.port.out.command.NotificationCommand;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import java.util.List;

public interface FindActivityNotificationsPort {

  List<NotificationCommand> findActivityNotifications(Long useId);

  ActivityNotification findUserActivityNotification(Long activityNotificationId);

  List<ActivityNotification> findUserActivityNotificationByBill(List<Long> billIds);

  List<ActivityNotificationCommand> findActivityNotifications(String userEmail);

  ActivityNotification findActivityNotification(Long activityNotificationId);

}
