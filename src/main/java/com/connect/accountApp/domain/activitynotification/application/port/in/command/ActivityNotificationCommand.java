package com.connect.accountApp.domain.activitynotification.application.port.in.command;

import com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActivityNotificationCommand {

  private Long userActivityNotificationId;
  private NotiCategory notiCategory;
  private Boolean isRead;
  private LocalDateTime createdAt;
  private String trigger;

  public ActivityNotificationCommand(Long userActivityNotificationId, NotiCategory notiCategory, Boolean isRead, LocalDateTime createdAt, String trigger) {
    this.userActivityNotificationId = userActivityNotificationId;
    this.notiCategory = notiCategory;
    this.isRead = isRead;
    this.createdAt = createdAt;
    this.trigger = trigger;
  }
}
