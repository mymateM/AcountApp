package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.adapter.in.web.request.UpdateUserSettlementRequest;
import com.connect.accountApp.domain.user.application.port.in.command.UpdateUserSettlementRatioUseCase;
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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UpdateUserSettlementRatioController {

    private final UpdateUserSettlementRatioUseCase updateUserSettlementRatioUseCase;
    private final FcmNotificationUseCase fcmNotificationUseCase;

    @PostMapping("/roomates/settlement-ratio")
    public ResponseEntity authenticate(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateUserSettlementRequest request) {
        String userEmail = userDetails.getUsername();
        updateUserSettlementRatioUseCase.updateUserSettlementRatio(userEmail, request);


        Notification notification = Notification.builder()
                .setTitle("정산 비율 변경")
                .setBody("비율 변경 소식이에요! 내 비율은 얼마일까요?")
                .setImage("")
                .build();

        fcmNotificationUseCase.sendNotificationHouseholdMember(notification, userEmail);

        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
    }
}
