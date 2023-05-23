package com.connect.accountApp.domain.notification.application.port.out;

import com.connect.accountApp.domain.notification.application.port.out.command.NotificationCommand;
import java.util.List;

public interface FindActivityNotificationsPort {

  List<NotificationCommand> findActivityNotifications(Long useId);

}
