package com.connect.accountApp.domain.notification.application.service;

import com.connect.accountApp.domain.notification.application.port.in.GetExpenseNotificationUseCase;
import com.connect.accountApp.domain.notification.application.port.in.command.GetExpenseNotificationCommand;
import com.connect.accountApp.domain.notification.application.port.out.FindExpenseNotificationPort;
import com.connect.accountApp.domain.notification.application.port.out.command.FindExpenseNotificationCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetExpenseNotificationService implements GetExpenseNotificationUseCase {

  private final FindExpenseNotificationPort findExpenseNotificationPort;

  @Override
  public List<GetExpenseNotificationCommand> getExpenseNotification(Long userId) {

    List<FindExpenseNotificationCommand> commands = findExpenseNotificationPort.findExpenseNotification(userId);

    return commands.stream().map(GetExpenseNotificationCommand::new).toList();
  }
}
