package com.connect.accountApp.domain.activitynotification.adapter.in.web;

import com.connect.accountApp.domain.activitynotification.adapter.in.web.response.ActivityNotificationsResponse;
import com.connect.accountApp.domain.activitynotification.application.port.in.GetActivityNotificationsUseCase;
import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class GetActivityNotificationsController {

  private final GetActivityNotificationsUseCase getActivityNotificationsUseCase;

  @GetMapping("/activity")
  public ResponseEntity getActivityNotifications(@AuthenticationPrincipal UserDetails userDetails) {
    String userEmail = userDetails.getUsername();
    List<ActivityNotificationCommand> activityNotifications = getActivityNotificationsUseCase.getActivityNotifications(userEmail);

    ActivityNotificationsResponse response = new ActivityNotificationsResponse(activityNotifications);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
