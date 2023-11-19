package com.connect.accountApp.global.common.application.port.out;

import com.connect.accountApp.global.common.adapter.in.web.request.FcmNotificationRequest;
import com.google.firebase.messaging.Notification;

public interface FcmNotificationUseCase {

  String sendNotificationByToken(FcmNotificationRequest request);

  void sendNotificationHouseholdMember(Notification notification, String userEmail); //todo : userEmail -> household

  void sendNotificationHouseholdMember(Notification notification, Long householdId);

  void sendNotificationMember(Notification notification, Long userId);

}
