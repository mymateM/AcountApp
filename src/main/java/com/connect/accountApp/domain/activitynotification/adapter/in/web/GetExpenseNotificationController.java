package com.connect.accountApp.domain.activitynotification.adapter.in.web;

import com.connect.accountApp.domain.activitynotification.adapter.in.web.dto.GetExpenseNotificationResponse;
import com.connect.accountApp.domain.activitynotification.application.port.in.GetExpenseNotificationUseCase;
import com.connect.accountApp.domain.activitynotification.application.port.in.command.GetExpenseNotificationCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetExpenseNotificationController {

  private final GetExpenseNotificationUseCase getExpenseNotificationUseCase;

  @GetMapping("/notification/expense/{userId}")
  public ResponseEntity getExpenseNotification(@PathVariable Long userId) {

    List<GetExpenseNotificationCommand> commands = getExpenseNotificationUseCase.getExpenseNotification(userId);

    GetExpenseNotificationResponse response = new GetExpenseNotificationResponse(commands);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
