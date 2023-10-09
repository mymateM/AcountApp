package com.connect.accountApp.domain.activitynotification.adapter.in.web.controller;

import com.connect.accountApp.domain.activitynotification.application.port.in.UpdateActivityNotificationUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class UpdateActivityNotificationsController {

  private final UpdateActivityNotificationUseCase updateActivityNotificationUseCase;

  @PostMapping("/activity/is-read/true")
  public ResponseEntity changeIsReadInActivityNotificationsToTrue(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestParam("activity_notification_ids") List<Long> activity_notification_ids) {

    updateActivityNotificationUseCase.changeIsReadActivityNotificationsToTrue(activity_notification_ids);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
  }


}
