package com.connect.accountApp.domain.activitynotification.application.port.in.command;

import com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory;
import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActivityNotificationCommand {

  private String categoryTitle;
  private String categoryImageUrl;
  private Boolean isRead;
  private LocalDate createdAt;
  private String trigger;

  public ActivityNotificationCommand(NotiCategory notiCategory, Boolean isRead, LocalDate createdAt, String trigger) {
    this.categoryTitle = notiCategory.getTitle();
    this.categoryImageUrl = notiCategory.getImgUrl();
    this.isRead = isRead;
    this.createdAt = createdAt;
    this.trigger = trigger;
  }
}
