package com.connect.accountApp.domain.notification.adapter.in.web;

import com.connect.accountApp.domain.notification.adapter.in.web.dto.GetActivityNotificationResponse;
import com.connect.accountApp.domain.notification.application.port.in.GetActivityNotificationsUseCase;
import com.connect.accountApp.domain.notification.application.port.in.command.ActivityNotificationsCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetActivityNotificationsController {

  private final GetActivityNotificationsUseCase getActivityNotificationsUseCase;

  @GetMapping("/notification/activity/{userId}")
  public ResponseEntity getActivityNotifications(@PathVariable Long userId) {

    List<ActivityNotificationsCommand> activityNotifications
        = getActivityNotificationsUseCase.getActivityNotification(userId);

    GetActivityNotificationResponse response = new GetActivityNotificationResponse(activityNotifications);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
