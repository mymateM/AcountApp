package com.connect.accountApp.domain.household.adapter.in.web.controller;

import static com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.UPDATE_SETTLEMENT_DATE;

import com.connect.accountApp.domain.household.adapter.in.web.request.UpdateSettlementDayOfMonthRequest;
import com.connect.accountApp.domain.household.application.port.in.UpdateSettlementDayOfMonthUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import com.connect.accountApp.global.common.application.port.out.FcmNotificationUseCase;
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

    Notification notification = Notification.builder()
        .setTitle(UPDATE_SETTLEMENT_DATE.getTitle())
        .setBody("정산일이 " + request.getDayOfMonth() + "일로 바뀌었어요!.")
        .setImage(UPDATE_SETTLEMENT_DATE.getImgUrl())
        .build();

    fcmNotificationUseCase.sendNotificationHouseholdMember(notification, userEmail);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
  }
}
