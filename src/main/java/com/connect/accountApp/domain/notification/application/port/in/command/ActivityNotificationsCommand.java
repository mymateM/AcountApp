package com.connect.accountApp.domain.notification.application.port.in.command;

import com.connect.accountApp.domain.notification.application.port.out.command.NotificationCommand;
import com.connect.accountApp.domain.notification.domain.model.NotiCategory;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActivityNotificationsCommand {

  private NotiCategory notiCategory;
  private LocalDateTime notiCreatedAt;
  private String notiContent;
  private boolean notiIsRead;
  private String notiSender;

  public ActivityNotificationsCommand(NotificationCommand command) {

    this.notiCategory = command.getNotiCategory();
    this.notiCreatedAt = command.getNotiCreatedAt();
    this.notiContent = command.getNotiContent();
    this.notiIsRead = command.isNotiIsRead();
    this.notiSender = command.getUserName();
  }

}
