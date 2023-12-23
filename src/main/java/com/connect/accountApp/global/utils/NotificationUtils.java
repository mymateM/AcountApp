package com.connect.accountApp.global.utils;

import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.google.firebase.messaging.Notification;

public class NotificationUtils {

     public static Notification createBillNotification(BillCategory billCategory) {
        return Notification
                .builder()
                .setTitle("고지서")
                .setBody("새로운 고지서를 확인하세요!")
                .setImage(billCategory.getImgUrl())
                .build();
    }
}
