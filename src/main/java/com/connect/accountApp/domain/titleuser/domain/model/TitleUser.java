package com.connect.accountApp.domain.titleuser.domain.model;

import com.connect.accountApp.domain.title.domain.model.Title;
import com.connect.accountApp.domain.user.domain.model.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TitleUser {

  private Long titleUserId;
  private User user;
  private Title title;
  private LocalDateTime createdAt;

  @Builder
  public TitleUser(Long titleUserId, User user,
      Title title, LocalDateTime createdAt) {
    this.titleUserId = titleUserId;
    this.user = user;
    this.title = title;
    this.createdAt = createdAt;
  }
}
