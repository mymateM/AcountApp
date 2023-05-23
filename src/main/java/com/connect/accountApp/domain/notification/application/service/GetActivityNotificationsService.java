package com.connect.accountApp.domain.notification.application.service;

import com.connect.accountApp.domain.notification.application.port.in.GetActivityNotificationsUseCase;
import com.connect.accountApp.domain.notification.application.port.in.command.ActivityNotificationsCommand;
import com.connect.accountApp.domain.notification.application.port.out.FindActivityNotificationsPort;
import com.connect.accountApp.domain.notification.application.port.out.command.NotificationCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetActivityNotificationsService implements GetActivityNotificationsUseCase {

  private final FindActivityNotificationsPort findActivityNotificationsPort;


  @Override
  public List<ActivityNotificationsCommand> getActivityNotification(Long userId) {

    List<NotificationCommand> commands = findActivityNotificationsPort.findActivityNotifications(
        userId);
    return commands.stream().map(ActivityNotificationsCommand::new).toList();
  }
}
