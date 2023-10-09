package com.connect.accountApp.domain.usernotification.adapter.port.in.web.controller;

import com.connect.accountApp.domain.usernotification.application.port.in.UpdateUserActivityNotificationUseCase;
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
public class UpdateUserActivityNotificationsController {

  private final UpdateUserActivityNotificationUseCase updateUserActivityNotificationUseCase;

  @PostMapping("/activity/is-read/true")
  public ResponseEntity changeIsReadInActivityNotificationsToTrue(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestParam("activity_notification_ids") List<Long> activity_notification_ids) {

    updateUserActivityNotificationUseCase.changeIsReadUserActivityNotificationsToTrue(activity_notification_ids);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
  }


}
