package com.connect.accountApp.domain.titleuser.application.port.out.command;

import com.connect.accountApp.domain.title.domain.model.Title;
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

  public UserTitleCommand(LocalDateTime createdAt, Title title) {
    this.createdAt = createdAt;
    this.titleImg = title.getTitleImage();
    this.titleName = title.getTitleName();
  }
}
