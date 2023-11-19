package com.connect.accountApp.domain.settlement.adapter.in.web.controller;

import static com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.REQUEST_SETTLEMENT;

import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.bill.adapter.in.web.request.RegisterBillRequest;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.connect.accountApp.domain.settlement.application.port.in.SendMoneyUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import com.connect.accountApp.global.common.application.port.out.FcmNotificationUseCase;
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

        // 활동 알림 저장하기 -> 영서 쪽으로
        String requesterName = sendMoneyUseCase.saveActivityNotification(userEmail, userId);

        Notification notification = createNotification(requesterName);
        fcmNotificationUseCase
                .sendNotificationMember(notification, userId);
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
    }

    private Notification createNotification(String requesterName) {
        return Notification.builder()
                .setTitle(REQUEST_SETTLEMENT.getTitle())
                .setImage(REQUEST_SETTLEMENT.getImgUrl())
                .setBody("콕! " + requesterName + "님에게 이번 달 정산을 하러 가볼까요?")
                .build();

    }


}
