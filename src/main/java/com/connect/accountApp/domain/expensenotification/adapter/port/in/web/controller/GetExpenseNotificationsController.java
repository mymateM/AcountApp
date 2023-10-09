package com.connect.accountApp.domain.expensenotification.adapter.port.in.web.controller;

import com.connect.accountApp.domain.expensenotification.adapter.port.in.web.response.GetExpenseNotificationsResponse;
import com.connect.accountApp.domain.expensenotification.application.port.in.GetExpenseNotificationsUseCase;
import com.connect.accountApp.domain.expensenotification.application.port.in.command.ExpenseNotificationCommand;
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
public class GetExpenseNotificationsController {

  private final GetExpenseNotificationsUseCase getExpenseNotificationsUseCase;

  @GetMapping("/expense")
  public ResponseEntity getExpenseNotifications(@AuthenticationPrincipal UserDetails userDetails) {
    String userEmail = userDetails.getUsername();
    List<ExpenseNotificationCommand> commands = getExpenseNotificationsUseCase.getExpenseNotifications(userEmail);

    GetExpenseNotificationsResponse response = new GetExpenseNotificationsResponse(commands);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
