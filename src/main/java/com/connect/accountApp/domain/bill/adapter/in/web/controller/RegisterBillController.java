package com.connect.accountApp.domain.bill.adapter.in.web.controller;

import com.connect.accountApp.domain.bill.adapter.in.web.request.RegisterBillRequest;
import com.connect.accountApp.domain.bill.application.port.in.RegisterBillUseCase;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/bill")
public class RegisterBillController {

    private final RegisterBillUseCase registerBillUseCase;
    private final FcmNotificationUseCase fcmNotificationUseCase;

    @PostMapping("")
    public ResponseEntity registerBill(@AuthenticationPrincipal UserDetails userDetails,
                                       @RequestBody RegisterBillRequest request) {

        String userEmail = userDetails.getUsername();
        Long billId = registerBillUseCase.registerBill(userEmail, request);

        Notification notification =
                NotificationUtils.createBillNotification(BillCategory.valueOf(request.getBillCategoryTitle()));
        fcmNotificationUseCase.sendNotificationHouseholdMember(notification, userEmail);

        return ResponseEntity.ok(SuccessResponse.create201CreatedResponse(billId));
    }


}
