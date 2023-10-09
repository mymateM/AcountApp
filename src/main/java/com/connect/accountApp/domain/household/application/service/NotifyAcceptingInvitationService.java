package com.connect.accountApp.domain.household.application.service;

import static com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.ACCEPT_INVITATION;

import com.connect.accountApp.domain.activitynotification.application.port.out.FindActivityNotificationsPort;
import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory;
import com.connect.accountApp.domain.household.application.port.in.NotifyAcceptingInvitationUseCase;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.domain.usernotification.application.port.out.SaveUserActivityNotificationPort;
import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifyAcceptingInvitationService implements NotifyAcceptingInvitationUseCase {

  private final FirebaseMessaging firebaseMessaging;
  private final GetUserPort getUserPort;
  private final FindHouseholdUserListPort findHouseholdUserListPort;
  private final GetHouseholdPort getHouseholdPort;

  private final FindActivityNotificationsPort findActivityNotificationsPort;

  private final SaveActivityNotificationPort saveActivityNotificationPort;
  private final SaveUserActivityNotificationPort saveUserActivityNotificationPort;

  @Override
  public void notifyAcceptingInvitation(String householdInviteCode, String acceptedMemberEmail) {

    Household household = getHouseholdPort.findHousehold(householdInviteCode);
    User invitee = getUserPort.findUser(acceptedMemberEmail);

    List<User> originalMembers = getOriginalMembers(household, invitee);
    createAndSaveMembersAcceptInvitingNotification(originalMembers, invitee);

    sendAcceptInvitingNotification(originalMembers);
  }

  private List<User> getOriginalMembers(Household household, User invitee) {
    List<User> currentMembers = findHouseholdUserListPort.findHouseholdMembers(household.getHouseholdId());
    return currentMembers.stream()
        .filter(member -> !member.getUserId().equals(invitee.getUserId()))
        .toList();
  }

  private ActivityNotification createActivityNotification(User invitee) {
    return ActivityNotification.builder()
        .activityNotificationCategory(ACCEPT_INVITATION)
        .isRead(false)
        .requester(invitee)
        .build();

  }

  private UserActivityNotification createUserActivityNotification(User user, ActivityNotification activityNotification) {
    return UserActivityNotification.builder()
        .user(user)
        .activityNotification(activityNotification)
        .build();
  }

  Long createAndSaveAcceptInvitingNotification(User invitee) {
    ActivityNotification activityNotification = createActivityNotification(invitee);
    return saveActivityNotificationPort.saveActivityNotification(activityNotification);
  }


  private void createAndSaveMembersAcceptInvitingNotification(List<User> users, User invitee) {

    Long activityNotificationId = createAndSaveAcceptInvitingNotification(invitee);
    ActivityNotification activityNotification = findActivityNotificationsPort.findUserActivityNotification(activityNotificationId);


    users.forEach(user -> {
          UserActivityNotification userActivityNotification = createUserActivityNotification(user, activityNotification);
          saveUserActivityNotificationPort.saveUserActivityNotification(userActivityNotification);
        }
    );
  }

  void sendAcceptInvitingNotification(List<User> users) {

    users.forEach(user -> {
          if (user.getDeviceToken() != null) {
            Message notificationMessage = createNotificationMessage(ACCEPT_INVITATION.getTitle(),
                ACCEPT_INVITATION.getTitle(),
                ACCEPT_INVITATION.getImgUrl(), ACCEPT_INVITATION, user.getDeviceToken());
            sendMessage(user, notificationMessage);
          }
        }

    );
  }

  private String sendMessage(User member, Message message) { //todo 예외처리

    if (member.getDeviceToken() == null)
      return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + member.getUserId();

    try {
      firebaseMessaging.send(message);
      return "알림을 성공적으로 전송했습니다. targetUserId=" + member.getUserId();
    } catch (FirebaseMessagingException e) {
      e.printStackTrace();
      return "알림 보내기를 실패하였습니다. targetUserId" + member.getUserId();
    }
  }


  private Message createNotificationMessage(String title, String body, String imageUrl, NotiCategory category, String deviceToken) {
    Notification notification = createNotification(title, body, imageUrl);
    Message message = createMessage(deviceToken, notification, new ConcurrentHashMap<>());

    return message;
  }


  private Notification createNotification(String title, String body, String imageUrl) {
    return Notification.builder()
        .setTitle(title)
        .setBody(body)
        .setImage(imageUrl)
        .build();
  }

  private Message createMessage(String deviceToken, Notification notification, Map<String, String> data) {

    return Message.builder()
        .setToken(deviceToken)
        .setNotification(notification)
        .putAllData(data)
        .build();
  }


}
