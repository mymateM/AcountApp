package com.connect.accountApp.domain.settlement.adapter.in.web.controller;

import com.connect.accountApp.domain.settlement.application.port.in.SendMoneyUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import com.connect.accountApp.global.common.application.port.out.FcmNotificationUseCase;
import com.connect.accountApp.global.utils.NotificationUtils;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;
    private final FcmNotificationUseCase fcmNotificationUseCase;

    @GetMapping("/send-money/{user_id}")
    public ResponseEntity getUserSettlement(@AuthenticationPrincipal UserDetails userDetails,
                                            @PathVariable("user_id") Long userId) {
        String userEmail = userDetails.getUsername();
        String requesterName = sendMoneyUseCase.saveActivityNotification(userEmail, userId);

        Notification notification = NotificationUtils.sendMoneyNotification(requesterName);
        fcmNotificationUseCase.sendNotificationMember(notification, userId);
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
    }
}
