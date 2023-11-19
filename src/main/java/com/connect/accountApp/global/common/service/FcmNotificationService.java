package com.connect.accountApp.global.common.service;

import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.global.common.adapter.in.web.request.FcmNotificationRequest;
import com.connect.accountApp.global.common.application.port.out.FcmNotificationUseCase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmNotificationService implements FcmNotificationUseCase {

    private final FirebaseMessaging firebaseMessaging;
    private final GetUserPort getUserPort;
    private final FindHouseholdUserListPort findHouseholdUserListPort;

    @Override
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
        } else {
            return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + request.getTargetUserId();
        }
    }

    @Override
    public void sendNotificationHouseholdMember(Notification notification, String userEmail) {

        User user = getUserPort.findUserWithHousehold(userEmail);
        List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(
                user.getHousehold().getHouseholdId());

        householdMembers.forEach(householdMember -> System.out.println(sendMessage(householdMember, notification)));
    }

    @Override
    public void sendNotificationHouseholdMember(Notification notification, Long householdId) {
        List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(householdId);
        householdMembers.forEach(householdMember -> System.out.println(sendMessage(householdMember, notification)));
    }

    @Override
    public void sendNotificationMember(Notification notification, Long userId) {
        User user = getUserPort.getUser(userId);
        System.out.println(sendMessage(user, notification));
    }

    private String sendMessage(User user, Notification notification) {

        if (user.getDeviceToken() != null) {
            Message message = Message.builder()
                    .setToken(user.getDeviceToken())
                    .setNotification(notification)
                    .putAllData(new LinkedHashMap<>())
                    .build();

            try {
                firebaseMessaging.send(message);
                return "알림을 성공적으로 전송했습니다. targetUserId=" + user.getUserId();
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
                return "알림 보내기를 실패하였습니다. targetUserId" + user.getUserId();
            }
        } else {
            return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + user.getUserId();
        }
    }

}
