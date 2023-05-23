package com.connect.accountApp.domain.notification.application.port.in;

import com.connect.accountApp.domain.notification.application.port.in.command.ActivityNotificationsCommand;
import java.util.List;

public interface GetActivityNotificationsUseCase {

  List<ActivityNotificationsCommand> getActivityNotification(Long userId);


}
