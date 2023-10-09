package com.connect.accountApp.domain.usernotification.application.port.out;

import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
import java.util.List;

public interface FindUserActivityNotificationsPort {

  List<UserActivityNotification> findUserActivityNotification(List<Long> userActivityNotificationId);

}
