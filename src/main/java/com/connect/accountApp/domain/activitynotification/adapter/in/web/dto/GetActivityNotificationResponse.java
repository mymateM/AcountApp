package com.connect.accountApp.domain.activitynotification.adapter.in.web.dto;

import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationsCommand;
import com.connect.accountApp.global.utils.DateTimeUtils;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetActivityNotificationResponse {

  private List<NotificationActivity> noti_activites;

  public GetActivityNotificationResponse(List<ActivityNotificationsCommand> commands) {
    this.noti_activites = commands.stream().map(NotificationActivity::new).toList();
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  private class NotificationActivity {

    private String noti_category;
    private String noti_img_url;
    private String noti_created_at;
    private String noti_content;
    private boolean noti_is_read;
    private String noti_sender;

    // TODO : EnumTypeValue 제대로 사용
    // TODO : bill, expense, user id값 전달 추가
    public NotificationActivity(ActivityNotificationsCommand command) {
      this.noti_category = command.getNotiCategory().getTitle();
      this.noti_img_url = command.getNotiCategory().getImgUrl();
      this.noti_created_at = DateTimeUtils.timesAgo(command.getNotiCreatedAt());
      this.noti_content = command.getNotiContent();
      this.noti_is_read = command.isNotiIsRead();
      this.noti_sender = command.getNotiSender();
    }

  }

}
