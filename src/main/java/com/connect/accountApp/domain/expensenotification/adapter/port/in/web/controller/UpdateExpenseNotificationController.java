package com.connect.accountApp.domain.expensenotification.adapter.port.in.web.controller;

import com.connect.accountApp.domain.expensenotification.application.port.in.UpdateExpenseNotificationUseCase;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class UpdateExpenseNotificationController {

  private final UpdateExpenseNotificationUseCase updateExpenseNotificationUseCase;

  @PostMapping("/expense/is-read/true")
  public ResponseEntity changeIsReadInExpenseNotificationsToTrue(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestParam("expense_notification_ids") List<Long> expenseNotificationIds) {
    updateExpenseNotificationUseCase.changeIsReadInExpenseNotificationsToTrue(expenseNotificationIds);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
  }

}
