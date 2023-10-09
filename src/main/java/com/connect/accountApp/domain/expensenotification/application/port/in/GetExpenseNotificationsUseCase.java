package com.connect.accountApp.domain.expensenotification.application.port.in;

import com.connect.accountApp.domain.expensenotification.application.port.in.command.ExpenseNotificationCommand;
import java.util.List;

public interface GetExpenseNotificationsUseCase {

  List<ExpenseNotificationCommand> getExpenseNotifications(String userEmail);

}
