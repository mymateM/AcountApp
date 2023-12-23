package com.connect.accountApp.global.utils;

import static com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.UPDATE_SETTLEMENT_DATE;

import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.google.firebase.messaging.Notification;
import java.math.BigDecimal;

public class NotificationUtils {

    public static Notification createBillNotification(BillCategory billCategory) {
        return Notification
                .builder()
                .setTitle("고지서")
                .setBody("새로운 고지서를 확인하세요!")
                .setImage(billCategory.getImgUrl())
                .build();
    }

    public static Notification updateBudgetNotification(BigDecimal householdBudget) {
        return Notification.builder()
                .setTitle("예산 변경")
                .setBody("다음 정산 때 예산이 " + householdBudget + "으로 바뀔 예정이에요!")
                .setImage("")
                .build();
    }

    public static Notification updateSettlementDate(Integer dayOfMonth) {
        return Notification.builder()
                .setTitle(UPDATE_SETTLEMENT_DATE.getTitle())
                .setBody("정산일이 " + dayOfMonth + "일로 바뀌었어요!.")
                .setImage(UPDATE_SETTLEMENT_DATE.getImgUrl())
                .build();
    }
}
