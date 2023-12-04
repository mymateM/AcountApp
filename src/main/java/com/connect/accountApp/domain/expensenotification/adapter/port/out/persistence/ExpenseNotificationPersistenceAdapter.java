package com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence;

import com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence.jpa.ExpenseNotificationJpaRepository;
import com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence.jpa.model.ExpenseNotificationJpaEntity;
import com.connect.accountApp.domain.expensenotification.application.port.in.command.ExpenseNotificationCommand;
import com.connect.accountApp.domain.expensenotification.application.port.in.command.FindSpenderCommand;
import com.connect.accountApp.domain.expensenotification.application.port.out.DeleteExpenseNotificationPort;
import com.connect.accountApp.domain.expensenotification.application.port.out.FindExpenseNotificationPort;
import com.connect.accountApp.domain.expensenotification.application.port.out.SaveExpenseNotificationPort;
import com.connect.accountApp.domain.expensenotification.domain.model.ExpenseNotification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExpenseNotificationPersistenceAdapter implements FindExpenseNotificationPort,
    SaveExpenseNotificationPort, DeleteExpenseNotificationPort {

  private final ExpenseNotificationMapper expenseNotificationMapper;
  private final ExpenseNotificationQueryRepository expenseNotificationQueryRepository;
  private final ExpenseNotificationJpaRepository expenseNotificationJpaRepository;



  @Override
  public List<ExpenseNotificationCommand> findExpenseNotifications(String userEmail) {
    return expenseNotificationQueryRepository.findExpenseNotificationsInHousehold(userEmail);
  }

  @Override
  public List<FindSpenderCommand> findExpenseSpenders(List<Long> expenseId) {
    return expenseNotificationQueryRepository.findSpender(expenseId);
  }

  @Override
  public List<ExpenseNotification> findExpenseNotifications(List<Long> expenseNotificationIds) {
    List<ExpenseNotificationJpaEntity> expenseNotifications =
        expenseNotificationQueryRepository.findExpenseNotifications(expenseNotificationIds);
    return expenseNotifications.stream().map(expenseNotificationMapper::mapToDomainEntity).toList();
  }

  @Override
  public List<ExpenseNotification> findExpenseNotifications(Long expenseId) {
    List<ExpenseNotificationJpaEntity> expenseNotifications =
            expenseNotificationQueryRepository.findExpenseNotifications(expenseId);
    return expenseNotifications.stream().map(expenseNotificationMapper::mapToDomainEntity).toList();
  }

  @Override
  public void saveAll(List<ExpenseNotification> expenseNotifications) {
    List<ExpenseNotificationJpaEntity> expenseNotificationJpaEntities = expenseNotifications.stream()
        .map(expenseNotificationMapper::mapToJpaEntity).toList();
    expenseNotificationJpaRepository.saveAll(expenseNotificationJpaEntities);

  }

  @Override
  public void deleteExpenseNotification(List<Long> expenseIds) {
    expenseNotificationJpaRepository.deleteAllById(expenseIds);
  }
}
