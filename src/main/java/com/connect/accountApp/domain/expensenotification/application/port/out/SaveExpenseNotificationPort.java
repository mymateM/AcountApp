package com.connect.accountApp.domain.expensenotification.application.port.out;

import com.connect.accountApp.domain.expensenotification.domain.model.ExpenseNotification;
import java.util.List;

public interface SaveExpenseNotificationPort {

  void saveAll(List<ExpenseNotification> expenseNotifications);

}
