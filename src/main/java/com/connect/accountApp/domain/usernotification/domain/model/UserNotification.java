package com.connect.accountApp.domain.usernotification.domain.model;

import com.connect.accountApp.domain.activitynotification.domain.model.Notification;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNotification {

  private Long userNotiId;
  private User user;
  private Notification notification;

  @Builder
  public UserNotification(Long userNotiId, User user,
      Notification notification) {
    this.userNotiId = userNotiId;
    this.user = user;
    this.notification = notification;
  }
}
