package com.connect.accountApp.domain.notification.application.port.in;

import com.connect.accountApp.domain.notification.application.port.in.command.GetExpenseNotificationCommand;
import java.util.List;

public interface GetExpenseNotificationUseCase {

  List<GetExpenseNotificationCommand> getExpenseNotification(Long userId);

}
