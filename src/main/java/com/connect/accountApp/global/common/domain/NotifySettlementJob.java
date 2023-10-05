package com.connect.accountApp.global.common.domain;

import com.connect.accountApp.global.common.application.port.out.FcmNotificationUseCase;
import com.google.firebase.messaging.Notification;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class NotifySettlementJob implements Job {

  private FcmNotificationUseCase fcmNotificationUseCase;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    Notification notification = Notification.builder()
        .setTitle("정산 디데이")
        .setBody("정산 일이 다가왔어요! 정산을 하러 가볼까요?")
        .setImage("")
        .build();

    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    Long householdId = dataMap.getLong("householdId");

    fcmNotificationUseCase.sendNotificationHouseholdMember(notification, householdId);
  }
}
