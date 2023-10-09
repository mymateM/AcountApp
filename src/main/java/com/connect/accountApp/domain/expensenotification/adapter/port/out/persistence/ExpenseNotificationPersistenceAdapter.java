package com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence;

import com.connect.accountApp.domain.expensenotification.application.port.in.command.ExpenseNotificationCommand;
import com.connect.accountApp.domain.expensenotification.application.port.in.command.FindSpenderCommand;
import com.connect.accountApp.domain.expensenotification.application.port.out.FindExpenseNotificationPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExpenseNotificationPersistenceAdapter implements FindExpenseNotificationPort {

  private final ExpenseNotificationMapper expenseNotificationMapper;
  private final ExpenseNotificationQueryRepository expenseNotificationQueryRepository;


  @Override
  public List<ExpenseNotificationCommand> findExpenseNotifications(String userEmail) {
    return expenseNotificationQueryRepository.findExpenseNotificationsInHousehold(userEmail);
  }

  @Override
  public List<FindSpenderCommand> findExpenseSpenders(List<Long> expenseId) {
    return expenseNotificationQueryRepository.findSpender(expenseId);
  }
}
