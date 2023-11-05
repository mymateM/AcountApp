package com.connect.accountApp.domain.activitynotification.domain.model;

import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.user.domain.model.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivityNotification {

  private Long activityNotificationId;
  private NotiCategory activityNotificationCategory;
  private String title;
  private String message;
  private Boolean isRead;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Bill bill;
  private User requester;


  @Builder
  public ActivityNotification(Long activityNotificationId,
      NotiCategory activityNotificationCategory, String title, String message,
      Boolean isRead, LocalDateTime createdAt, LocalDateTime modifiedAt,
      Bill bill, User requester) {
    this.activityNotificationId = activityNotificationId;
    this.activityNotificationCategory = activityNotificationCategory;
    this.title = title;
    this.message = message;
    this.isRead = isRead;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.bill = bill;
    this.requester = requester;
  }

  public void changeIsReadToTrue() {
    setRead(true);
  }
  private void setRead(Boolean read) {
    isRead = read;
  }

  public void setBillNull() {
    bill = null;
  }
}
