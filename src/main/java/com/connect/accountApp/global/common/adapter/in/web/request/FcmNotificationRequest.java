package com.connect.accountApp.global.common.adapter.in.web.request;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FcmNotificationRequest {

  private Long targetUserId;
  private String title;
  private String body;
  private String image;
  private Map<String, String> data;

  @Builder
  public FcmNotificationRequest(Long targetUserId, String title, String body, String image,
      Map<String, String> data) {
    this.targetUserId = targetUserId;
    this.title = title;
    this.body = body;
    this.image = image;
    this.data = data;
  }
}
