package com.connect.accountApp.domain.expensenotification.application.port.in;

import java.util.List;

public interface UpdateExpenseNotificationUseCase {

  void changeIsReadInExpenseNotificationsToTrue(List<Long> expenseNotificationIds);

}
