package com.connect.accountApp.domain.notification.adapter.out.persistence;

import com.connect.accountApp.domain.notification.application.port.out.FindActivityNotificationsPort;
import com.connect.accountApp.domain.notification.application.port.out.command.NotificationCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements FindActivityNotificationsPort {

  private final NotificationQueryRepository notificationQueryRepository;

  @Override
  public List<NotificationCommand> findActivityNotifications(Long useId) {
    return notificationQueryRepository.findActivityNotifications(useId);
  }
}
