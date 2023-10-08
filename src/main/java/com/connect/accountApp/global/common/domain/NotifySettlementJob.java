package com.connect.accountApp.global.common.domain;

import com.connect.accountApp.global.common.application.port.out.FcmNotificationUseCase;
import com.connect.accountApp.global.common.service.FcmNotificationService;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@RequiredArgsConstructor
public class NotifySettlementJob extends QuartzJobBean {

  @Override
  public void executeInternal(JobExecutionContext context) throws JobExecutionException {

    ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail()
        .getJobDataMap().get("applicationContext");

    FcmNotificationUseCase fcmNotificationService = applicationContext.getBean(FcmNotificationService.class);

    Notification notification = Notification.builder()
        .setTitle("정산 디데이")
        .setBody("정산 일이 다가왔어요! 정산을 하러 가볼까요?")
        .setImage("")
        .build();

    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    Long householdId = dataMap.getLong("householdId");

    fcmNotificationService.sendNotificationHouseholdMember(notification, householdId);
  }
}
