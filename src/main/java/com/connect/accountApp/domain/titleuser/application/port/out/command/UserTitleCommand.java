package com.connect.accountApp.domain.titleuser.application.port.out.command;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTitleCommand {

  private LocalDateTime createdAt;
  private String titleImg;
  private String titleName;

  public UserTitleCommand(LocalDateTime createdAt, String titleImg, String titleName) {
    this.createdAt = createdAt;
    this.titleImg = titleImg;
    this.titleName = titleName;
  }
}
