package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.adapter.in.web.request.UpdateSettlementDayOfMonthRequest;
import com.connect.accountApp.domain.household.application.port.in.UpdateSettlementDayOfMonthUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import com.connect.accountApp.global.common.application.port.out.FcmNotificationUseCase;
import com.connect.accountApp.global.utils.NotificationUtils;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/household")
@RequiredArgsConstructor
public class UpdateHouseholdSettlementDateController {

  private final UpdateSettlementDayOfMonthUseCase updateSettlementDayOfMonthUseCase;
  private final FcmNotificationUseCase fcmNotificationUseCase;

  @PostMapping("/settlement-date")
  public ResponseEntity registerHousehold(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateSettlementDayOfMonthRequest request) {

    String userEmail = userDetails.getUsername();
    updateSettlementDayOfMonthUseCase.updateSettlementDayOfMonth(userEmail, request.getDayOfMonth());
    Notification notification = NotificationUtils.updateSettlementDate(request.getDayOfMonth());

    fcmNotificationUseCase.sendNotificationHouseholdMember(notification, userEmail);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
  }
}
