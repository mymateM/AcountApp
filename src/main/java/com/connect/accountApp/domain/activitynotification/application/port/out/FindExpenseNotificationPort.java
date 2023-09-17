package com.connect.accountApp.domain.activitynotification.application.port.out;

import com.connect.accountApp.domain.activitynotification.application.port.out.command.FindExpenseNotificationCommand;
import java.util.List;

public interface FindExpenseNotificationPort {

  List<FindExpenseNotificationCommand> findExpenseNotification(Long userId);

}
