package com.connect.accountApp.global.common.application.port.out;

import com.connect.accountApp.global.common.adapter.in.web.request.FcmNotificationRequest;
import com.google.firebase.messaging.Notification;

public interface FcmNotificationUseCase {

  String sendNotificationByToken(FcmNotificationRequest request);

  void sendNotificationHouseholdMember(Notification request, String userEmail);

}
