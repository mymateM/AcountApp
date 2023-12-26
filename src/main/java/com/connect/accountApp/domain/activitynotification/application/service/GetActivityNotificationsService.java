package com.connect.accountApp.domain.activitynotification.application.service;

import com.connect.accountApp.domain.activitynotification.application.port.in.GetActivityNotificationsUseCase;
import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationCommand;
import com.connect.accountApp.domain.activitynotification.application.port.out.FindActivityNotificationsPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetActivityNotificationsService implements GetActivityNotificationsUseCase {

  private final FindActivityNotificationsPort findActivityNotificationsPort;

  @Override
  public List<ActivityNotificationCommand> getActivityNotifications(String userEmail) {
    return findActivityNotificationsPort.findActivityNotifications(userEmail);
  }
}
