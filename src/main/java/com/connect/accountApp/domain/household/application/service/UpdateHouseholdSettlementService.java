package com.connect.accountApp.domain.household.application.service;

import static com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.WILL_UPDATE_BUDGET;
import static java.math.RoundingMode.FLOOR;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.household.application.port.in.UpdateHouseholdSettlementUseCase;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.global.common.domain.NotifyUpdatedBudgetJob;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateHouseholdSettlementService implements UpdateHouseholdSettlementUseCase {

//  private final SaveHouseholdPort saveHouseholdPort;
  private final GetUserPort getUserPort;
  private final SaveActivityNotificationPort saveActivityNotificationPort;

  private final ApplicationContext applicationContext;

  @Override
  public BigDecimal updateHouseholdSettlement(String userEmail, BigDecimal newBudgetAmount) {

    User user = getUserPort.findUserWithHousehold(userEmail);
    Household household = user.getHousehold();

//    household.updateHouseholdBudget(budgetAmount);
    household.setSettlementWillBeUpdatedToTrue();
//    saveHouseholdPort.saveHousehold(household);

    ActivityNotification activityNotification = createUpdateSettlementDateNotification(newBudgetAmount, user);
    saveActivityNotificationPort.saveActivityNotification(activityNotification);

    // 정산이 있고 난 뒤, 정산일 다음 날부터 이번 정산은 00원의 예산을 가지고 정산할 것이라고 말해줘야함.
    createUpdateSettlementDateNotificationScheduler(household, user, newBudgetAmount);

    return household.getHouseholdBudget();
  }

  private ActivityNotification createUpdateSettlementDateNotification(BigDecimal newHouseholdBudget, User user) {
    return ActivityNotification.builder()
        .activityNotificationCategory(WILL_UPDATE_BUDGET)
        .title(WILL_UPDATE_BUDGET.getTitle())
        .message("다음 정산 때 예산이 " + newHouseholdBudget.setScale(0, FLOOR) + "원으로 바뀔 예정이에요!")
        .isRead(false)
        .requester(user)
        .build();
  }

  private void createUpdateSettlementDateNotificationScheduler(Household household, User user, BigDecimal newBudgetAmount) {
    try {
      SchedulerFactory schedulerFactory = new StdSchedulerFactory();
      Scheduler scheduler = schedulerFactory.getScheduler();

      JobDetail job = getJobDetail(household, user, newBudgetAmount);
      Trigger trigger = getTrigger(household);

      scheduler.scheduleJob(job, trigger);
      scheduler.start();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  private SimpleTrigger getTrigger(Household household) {
    Date notifyDate = getNotifyDate(household);

    return (SimpleTrigger) newTrigger()
        .withIdentity("notifyBudgetUpdatedTrigger", "notifyTriggerGroup")
        .startAt(notifyDate)
        .forJob("notifyBudgetUpdated", "notifyGroup")
        .build();
  }

  private Date getNotifyDate(Household household) {
    Integer householdSettlementDayOfMonth = household.getHouseholdSettlementDayOfMonth();
    LocalDate now = LocalDate.now();

    LocalDate notifyLocalDate = getNotifyLocalDate(householdSettlementDayOfMonth, now);
    Date notifyDate = fromNotifyLocalDateToNotifyDate(notifyLocalDate);
    return notifyDate;
  }

  private LocalDate getNotifyLocalDate(Integer householdSettlementDayOfMonth, LocalDate now) {
    if (now.getDayOfMonth() >= householdSettlementDayOfMonth) {
      // 이번달, 이번년도, 이번 정산일로 date 생성 -> 한달 더하고 하루 더함
      return LocalDate.of(now.getYear(), now.getMonth(), householdSettlementDayOfMonth).plusMonths(1).plusDays(1);
    } else {
      // 이번달 이번년도 이번 정산일로 date 생성 -> 하루 더함
      return LocalDate.of(now.getYear(), now.getMonth(), householdSettlementDayOfMonth).plusDays(1);
    }
  }

  private Date fromNotifyLocalDateToNotifyDate(LocalDate notifyLocalDate) {
    Instant instant = notifyLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    return Date.from(instant);
  }

  private JobDetail getJobDetail(Household household, User user, BigDecimal newBudgetAmount) {
    JobDataMap jobDataMap = getJobDataMap(household, user, newBudgetAmount);
    return newJob(NotifyUpdatedBudgetJob.class)
        .withIdentity("notifyBudgetUpdated", "notifyGroup")
        .withDescription("예산이 변경되었음을 알리는 역할")
        .usingJobData(jobDataMap)
        .build();
  }

  private JobDataMap getJobDataMap(Household household, User user, BigDecimal newBudgetAmount) {
    Map<String, Object> map = new LinkedHashMap<>();
    map.put("applicationContext", applicationContext);
    map.put("household", household);
    map.put("user", user);
    map.put("newBudgetAmount", newBudgetAmount);

    return new JobDataMap(map);
  }

}
