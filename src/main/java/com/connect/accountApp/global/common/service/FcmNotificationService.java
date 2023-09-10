package com.connect.accountApp.global.common.service;

import com.connect.accountApp.domain.user.adapter.out.persistence.UserJpaRepository;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.global.common.adapter.in.web.request.FcmNotificationRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmNotificationService {

  private final FirebaseMessaging firebaseMessaging;
  private final GetUserPort getUserPort;

  public String sendNotificationByToken(FcmNotificationRequest request) {

    User user = getUserPort.getUser(request.getTargetUserId());

    if (user.getDeviceToken() != null) {

      Notification notification = Notification.builder()
          .setTitle(request.getTitle())
          .setBody(request.getBody())
          .setImage(request.getImage())
          .build();

      Message message = Message.builder()
          .setToken(user.getDeviceToken())
          .setNotification(notification)
          .putAllData(request.getData())
          .build();

      try {
        firebaseMessaging.send(message);
        return "알림을 성공적으로 전송했습니다. targetUserId=" + request.getTargetUserId();
      } catch (FirebaseMessagingException e) {
        e.printStackTrace();
        return "알림 보내기를 실패하였습니다. targetUserId" + request.getTargetUserId();
      }
    } else{
      return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + request.getTargetUserId();
    }
  }

}