package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.application.port.in.UpdateHouseholdSettlementUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import com.connect.accountApp.global.common.application.port.out.FcmNotificationUseCase;
import com.google.firebase.messaging.Notification;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/household")
@RequiredArgsConstructor
public class UpdateHouseholdSettlementController {

  private final UpdateHouseholdSettlementUseCase updateHouseholdSettlementUseCase;
  private final FcmNotificationUseCase fcmNotificationUseCase;

  @GetMapping("/settlement/{budget_amount}/{allowance_ratio}")
  public ResponseEntity registerHousehold(@AuthenticationPrincipal UserDetails userDetails,
                                          @PathVariable("budget_amount") BigDecimal budgetAmount,
                                          @PathVariable("allowance_ratio") Integer allowanceRatio) {

    String userEmail = userDetails.getUsername();
    BigDecimal householdBudget = updateHouseholdSettlementUseCase.updateHouseholdSettlement(userEmail, budgetAmount, allowanceRatio);


    Notification notification = Notification.builder()
        .setTitle("예산 변경")
        .setBody("다음 정산 때 예산이 " + householdBudget + "으로 바뀔 예정이에요!")
        .setImage("")
        .build();

    fcmNotificationUseCase.sendNotificationHouseholdMember(notification, userEmail);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());

  }

}
