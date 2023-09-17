package com.connect.accountApp.domain.activitynotification.exception;

import static com.connect.accountApp.global.error.ErrorCode.ACTIVITY_NOTIFICATION_NOT_FOUND;

import com.connect.accountApp.global.error.exception.BusinessException;

public class ActivityNotificationNotFoundException extends BusinessException {

  public ActivityNotificationNotFoundException(String message) {
    super(message, ACTIVITY_NOTIFICATION_NOT_FOUND);
  }

}
