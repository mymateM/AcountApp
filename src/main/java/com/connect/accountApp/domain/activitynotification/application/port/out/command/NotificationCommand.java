package com.connect.accountApp.domain.activitynotification.application.port.out.command;

import com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationCommand {

  private Long notiId;
  private LocalDateTime notiCreatedAt;
  private NotiCategory notiCategory;
  private String notiContent;
  private boolean notiIsRead;
  private String userName;

  public NotificationCommand(Long notiId, LocalDateTime notiCreatedAt,
      NotiCategory notiCategory, String notiContent, boolean notiIsRead, String userName) {
    this.notiId = notiId;
    this.notiCreatedAt = notiCreatedAt;
    this.notiCategory = notiCategory;
    this.notiContent = notiContent;
    this.notiIsRead = notiIsRead;
    this.userName = userName;
  }
}
