package com.connect.accountApp.domain.usernotification.domain.model;

import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserActivityNotification {

  private Long userNotiId;
  private User user;
  private ActivityNotification activityNotification;
  private Boolean isRead;

  @Builder
  public UserActivityNotification(Long userNotiId, User user,
      ActivityNotification activityNotification, Boolean isRead) {
    this.userNotiId = userNotiId;
    this.user = user;
    this.activityNotification = activityNotification;
    this.isRead = isRead;
  }
}
