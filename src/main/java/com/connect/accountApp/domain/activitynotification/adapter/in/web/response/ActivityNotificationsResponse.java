package com.connect.accountApp.domain.activitynotification.adapter.in.web.response;

import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActivityNotificationsResponse {

  List<ActivityNotificationResponse> activityNotificationResponses;

  public ActivityNotificationsResponse(List<ActivityNotificationCommand> commands) {
    this.activityNotificationResponses = commands.stream()
        .map(ActivityNotificationResponse::new).toList();
  }

  @Data
  @NoArgsConstructor
  public class ActivityNotificationResponse {

    @JsonProperty("activity_notification_id")
    private Long userActivityNotificationId;
    @JsonProperty("category_title")
    private String categoryTitle;
    @JsonProperty("category_image_url")
    private String categoryImageUrl;
    @JsonProperty("is_read")
    private Boolean isRead;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("trigger")
    private String trigger;

    public ActivityNotificationResponse(ActivityNotificationCommand command) {
      this.userActivityNotificationId = command.getUserActivityNotificationId();
      this.categoryTitle = command.getNotiCategory().getTitle();
      this.categoryImageUrl = command.getNotiCategory().getImgUrl();
      this.isRead = command.getIsRead();
      this.createdAt = command.getCreatedAt();
      this.trigger = command.getTrigger();
    }
  }


}
