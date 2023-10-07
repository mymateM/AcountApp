package com.connect.accountApp.domain.household.application.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.connect.accountApp.domain.household.adapter.in.web.request.RegisterHouseholdRequest;
import com.connect.accountApp.domain.household.application.port.in.RegisterHouseholdUseCase;
import com.connect.accountApp.domain.household.application.port.out.SaveHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.global.common.domain.NotifySettlementJob;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterHouseholdService implements RegisterHouseholdUseCase {

  private final SaveHouseholdPort saveHouseholdPort;
  private final GetUserPort getUserPort;
  private final SaveUserPort saveUserPort;

  private final ApplicationContext applicationContext;

  @Override
  public String registerHousehold(String userEmail, RegisterHouseholdRequest request) {

    Household defaultHousehold = createDefaultHouseholdByRequest(request);
    Household savedHousehold = saveHouseholdPort.saveHousehold(defaultHousehold);

    createSettlementNotificationScheduler(request.getHouseholdSettlementDayOfMonth(), savedHousehold.getHouseholdId());

    User user = getUserPort.findUser(userEmail);
    registerUserToHousehold(user, savedHousehold);

    return savedHousehold.getInviteCode();
  }

  private void createSettlementNotificationScheduler(int settlementDayOfMonth, Long householdId) {
    try {
      SchedulerFactory schedulerFactory = new StdSchedulerFactory();
      Scheduler scheduler = schedulerFactory.getScheduler();

      JobDetail job = getJobDetail(householdId);
      Trigger trigger = getTrigger(settlementDayOfMonth);

      scheduler.scheduleJob(job, trigger);
      scheduler.start();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  private Trigger getTrigger(int settlementDayOfMonth) {
    return newTrigger()
        .withIdentity("testTrigger", "testGroup")
        .startNow()
        .withSchedule(cronSchedule("0 0 10 " + settlementDayOfMonth + " 1/1 ? *"))
        .build();
  }

  private JobDetail getJobDetail(Long householdId) {
    JobDataMap jobDataMap = getJobDataMap(householdId);
    return newJob(NotifySettlementJob.class)
        .withIdentity("notifySettlement", "notifyGroup")
        .withDescription("정산일을 알리는 역할")
        .usingJobData(jobDataMap)
        .build();
  }

  private JobDataMap getJobDataMap(Long householdId) {
    Map<String, Object> map = new LinkedHashMap<>();
    map.put("applicationContext", applicationContext);
    map.put("householdId", householdId);

    return new JobDataMap(map);
  }


  private Household createDefaultHouseholdByRequest(RegisterHouseholdRequest request) {

    return createDefaultHousehold(request.getHouseholdName(),
        request.getHouseholdSettlementDayOfMonth(),
        request.getHouseholdBudget().getAmount(),
        request.getHouseholdBudget().getAllowanceRatio());
  }

  private Household createDefaultHousehold(String householdName, Integer settlementDayOfMonth, BigDecimal amount, Integer settlementAllowanceRatio) {

    String inviteCode = createInviteCodeOfHousehold();

    return Household.builder()
        .householdName(householdName)
        .householdSettlementDayOfMonth(settlementDayOfMonth)
        .householdSettlementDate(LocalDateTime.now().toLocalDate())
        .householdBudget(amount)
        .householdBudgetAllowanceRatio(settlementAllowanceRatio)
        .settlementWillBeUpdated(false)
        .inviteCode(inviteCode)
        .build();
  }


  private void registerUserToHousehold(User user, Household household) {
    user.registerUserToHousehold(household);
    user.updateUserSettlementRatio(100);
    saveUserPort.save(user);
  }

  private String createInviteCodeOfHousehold() { // todo 중복 문제
    return UUID.randomUUID().toString();
  }


}
