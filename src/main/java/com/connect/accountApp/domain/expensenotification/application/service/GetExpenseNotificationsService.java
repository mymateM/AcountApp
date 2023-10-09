package com.connect.accountApp.domain.expensenotification.application.service;

import com.connect.accountApp.domain.expensenotification.application.port.in.command.FindSpenderCommand;
import com.connect.accountApp.domain.expensenotification.application.port.out.FindExpenseNotificationPort;
import com.connect.accountApp.domain.expensenotification.application.port.in.GetExpenseNotificationsUseCase;
import com.connect.accountApp.domain.expensenotification.application.port.in.command.ExpenseNotificationCommand;
import com.connect.accountApp.domain.settlement.application.port.out.FindSettlementPort;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetExpenseNotificationsService implements GetExpenseNotificationsUseCase {

  private final FindExpenseNotificationPort findExpenseNotificationPort;

  @Override
  public List<ExpenseNotificationCommand> getExpenseNotifications(String userEmail) {
    List<ExpenseNotificationCommand> commands = findExpenseNotificationPort.findExpenseNotifications(userEmail);

    List<FindSpenderCommand> spenderCommands = FindSpenderByExpenseNotificationCommands(commands);
    settingSpenderInCommands(commands, spenderCommands);
    return commands;
  }

  private List<FindSpenderCommand> FindSpenderByExpenseNotificationCommands(List<ExpenseNotificationCommand> commands) {
    List<Long> expenseId = commands.stream().map(ExpenseNotificationCommand::getExpenseId).toList();
    return findExpenseNotificationPort.findExpenseSpenders(expenseId);
  }

  private void settingSpenderInCommands(List<ExpenseNotificationCommand> commands, List<FindSpenderCommand> spenderCommands) {
    Map<Long, String> spenderMap = convertToMap(spenderCommands);
    commands.forEach(command -> command.setSpenderName(spenderMap.get(command.getExpenseId())));
  }

  private Map<Long, String> convertToMap(List<FindSpenderCommand> spenderCommands) {
    return spenderCommands.stream().collect(
        Collectors.toMap(FindSpenderCommand::getExpenseId, FindSpenderCommand::getSpenderName));
  }
}
