package com.connect.accountApp.domain.expensenotification.application.service;

import com.connect.accountApp.domain.expensenotification.application.port.in.UpdateExpenseNotificationUseCase;
import com.connect.accountApp.domain.expensenotification.application.port.out.FindExpenseNotificationPort;
import com.connect.accountApp.domain.expensenotification.application.port.out.SaveExpenseNotificationPort;
import com.connect.accountApp.domain.expensenotification.domain.model.ExpenseNotification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateExpenseNotificationService implements UpdateExpenseNotificationUseCase {

  private final FindExpenseNotificationPort findExpenseNotificationPort;
  private final SaveExpenseNotificationPort saveExpenseNotificationPort;

  @Override
  public void changeIsReadInExpenseNotificationsToTrue(List<Long> expenseNotificationIds) {
    List<ExpenseNotification> expenseNotifications = findExpenseNotificationPort.findExpenseNotifications(expenseNotificationIds);
    expenseNotifications.forEach(ExpenseNotification::changeIsReadToTrue);
    saveExpenseNotificationPort.saveAll(expenseNotifications);
  }
}
