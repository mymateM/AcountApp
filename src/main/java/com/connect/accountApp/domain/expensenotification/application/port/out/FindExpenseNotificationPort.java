package com.connect.accountApp.domain.expensenotification.application.port.out;

import com.connect.accountApp.domain.expensenotification.application.port.in.command.ExpenseNotificationCommand;
import com.connect.accountApp.domain.expensenotification.application.port.in.command.FindSpenderCommand;
import java.util.List;

public interface FindExpenseNotificationPort {

  List<ExpenseNotificationCommand> findExpenseNotifications(String userEmail);

  List<FindSpenderCommand> findExpenseSpenders(List<Long> expenseId);

}
