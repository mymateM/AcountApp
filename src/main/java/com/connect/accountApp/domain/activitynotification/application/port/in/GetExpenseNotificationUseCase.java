package com.connect.accountApp.domain.activitynotification.application.port.in;

import com.connect.accountApp.domain.activitynotification.application.port.in.command.GetExpenseNotificationCommand;
import java.util.List;

public interface GetExpenseNotificationUseCase {

  List<GetExpenseNotificationCommand> getExpenseNotification(Long userId);

}
