package com.connect.accountApp.global.common.domain;

import static com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.WILL_UPDATE_BUDGET;

import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.NotificationPersistenceAdapter;
import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.household.adapter.out.persistence.HouseholdPersistenceAdapter;
import com.connect.accountApp.domain.household.application.port.out.SaveHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.global.common.application.port.out.FcmNotificationUseCase;
import com.connect.accountApp.global.common.service.FcmNotificationService;
import com.google.firebase.messaging.Notification;
import java.math.BigDecimal;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

// 예산이 지금 부터 변경되었음
public class NotifyUpdatedBudgetJob extends QuartzJobBean {

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    Household household = (Household) dataMap.get("household");
    User user = (User) dataMap.get("user");
    BigDecimal newBudgetAmount = (BigDecimal) dataMap.get("newBudgetAmount");


    FcmNotificationUseCase fcmNotificationService = getFcmNotificationUseCase(context);
    Notification notification = createFcmNotification(newBudgetAmount);
    fcmNotificationService.sendNotificationHouseholdMember(notification, household.getHouseholdId());

    SaveActivityNotificationPort saveActivityNotificationPort = getNotificationPersistenceAdapter(context);
    ActivityNotification updatedBudgetNotification = createUpdatedBudgetNotification(newBudgetAmount, user);
    saveActivityNotificationPort.saveActivityNotification(updatedBudgetNotification);

    //이 시점에 예산을 바꿔야한다.
    // willbeUpdated가 true인지 확인, 맞다면 바꿔준 뒤 willbeUpdated를 false로 바꾼다.
    updateHouseholdBudgetAmount(household, newBudgetAmount, context);

  }

  private void updateHouseholdBudgetAmount(Household household, BigDecimal newBudgetAmount, JobExecutionContext context) {
    SaveHouseholdPort saveHouseholdPort = getHouseholdPersistenceAdapter(context);
    if (household.isSettlementWillBeUpdated()) {
      household.updateHouseholdBudget(newBudgetAmount);
      saveHouseholdPort.saveHousehold(household);
      household.setSettlementWillBeUpdatedToFalse();
    }
  }

  private SaveActivityNotificationPort getNotificationPersistenceAdapter(JobExecutionContext context) {
    ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
    return applicationContext.getBean(NotificationPersistenceAdapter.class);
  }

  private SaveHouseholdPort getHouseholdPersistenceAdapter(JobExecutionContext context) {
    ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
    return applicationContext.getBean(HouseholdPersistenceAdapter.class);
  }

  private Notification createFcmNotification(BigDecimal newBudgetAmount) {
    return Notification.builder()
        .setTitle("예산 변경")
        .setBody("이번 정산부터 예산은 " + newBudgetAmount + "+원이에요")
        .setImage("")
        .build();
  }

  private FcmNotificationUseCase getFcmNotificationUseCase(JobExecutionContext context) {
    ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
    return applicationContext.getBean(FcmNotificationService.class);
  }

  private ActivityNotification createUpdatedBudgetNotification(BigDecimal budgetAmount, User user) {
    return ActivityNotification.builder()
        .activityNotificationCategory(WILL_UPDATE_BUDGET)
        .title(WILL_UPDATE_BUDGET.getTitle())
        .message("이번 정산부터 예산은 " + budgetAmount + "+원이에요")
        .isRead(false)
        .requester(user)
        .build();
  }

}
