package com.connect.accountApp.global.common.domain;

import com.connect.accountApp.global.common.service.FcmNotificationService;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelloJob extends QuartzJobBean {



  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail()
        .getJobDataMap().get("applicationContext");

    FcmNotificationService fcmNotificationService = applicationContext.getBean(FcmNotificationService.class);

    Notification notification = Notification.builder()
        .setTitle("타이틀")
        .setBody("body")
        .setImage("")
        .build();

    fcmNotificationService.sendNotificationHouseholdMember(notification, 1L);

    System.err.println("Hello");
  }
}
